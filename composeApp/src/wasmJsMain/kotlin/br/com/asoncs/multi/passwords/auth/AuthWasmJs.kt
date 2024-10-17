package br.com.asoncs.multi.passwords.auth

import br.com.asoncs.multi.passwords.auth.AuthState.LoggedOut
import br.com.asoncs.multi.passwords.data.firebase.AuthRepository
import br.com.asoncs.multi.passwords.extension.error
import br.com.asoncs.multi.passwords.extension.isMobile
import br.com.asoncs.multi.passwords.external.*
import br.com.asoncs.multi.passwords.ui.login.TAG_LOGIN
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object AuthWasmJs : Auth, KoinComponent {

    private lateinit var app: JsAny
    private lateinit var auth: FirebaseAuth.Auth
    private lateinit var googleProvider: FirebaseAuth.GoogleAuthProvider

    override var emit: (AuthState) -> Unit = {}
    override val repository by inject<AuthRepository>()

    override suspend fun onAuthInit(
        emit: (AuthState) -> Unit
    ) {
        this.emit = emit

        app = FirebaseApp
            .initializeApp(firebaseConfig)
        // console.log("app", app)

        // val analytics = FirebaseAnalytics
        //     .getAnalytics(app)
        // console.log(analytics)

        googleProvider = FirebaseAuth
            .GoogleAuthProvider()
        // console.log("googleProvider", googleProvider)

        auth = FirebaseAuth
            .getAuth(app)
            .apply {
                languageCode = "it"
            }
        // console.log("auth", auth)

        /*
        val currentUser = auth.currentUser
        console.log("checkAuthState", currentUser)
        if (currentUser != null)
            currentUser.emitUser()
        else
            authState.emit(LoggedOut)
        */
        FirebaseAuth.onAuthStateChanged(auth) {
            val user = auth.currentUser
            // console.log("checkAuthState.onAuthStateChanged", user)
            if (user != null)
                user.emitUser()
            else
                emit(LoggedOut)
        }
    }

    override suspend fun login(
        password: String,
        username: String
    ) {
        FirebaseAuth
            .signInWithEmailAndPassword(
                auth = auth,
                email = username,
                password = password
            ).then {
                auth.currentUser.emitUser()
            }.catch {
                TAG_LOGIN.error("login")
                console.log(it)
                it
            }.await<JsAny>()
    }

    override suspend fun loginWithGoogle() {
        val isMobile = window.isMobile
        // TAG_LOGIN.log("loginWithGoogle| isMobile: $isMobile")
        // TODO Fix redirect method
        if (false && isMobile)
            loginWithGetRedirectResult()
        else
            loginWithSignInWithPopup()
    }

    override suspend fun logout() {
        FirebaseAuth
            .signOut(auth)
            .await<JsAny>()
        emit(LoggedOut)
    }

    override suspend fun signup(
        password: String,
        username: String
    ) {
        FirebaseAuth
            .createUserWithEmailAndPassword(
                auth = auth,
                email = username,
                password = password
            ).then {
                auth.currentUser.emitUser()
            }.catch {
                TAG_LOGIN.error("signup")
                console.log(it)
                it
            }.await<JsAny>()
    }

    private fun FirebaseAuth.User?.emitUser(): JsAny {
        if (this == null)
            throw AuthException.InvalidUserException

        emit(
            AuthState.LoggedIn(
                User(
                    name = displayName,
                    email = email,
                    photoUrl = photoURL,
                    uid = uid
                )
            )
        )

        return this
    }

    private suspend fun loginWithGetRedirectResult() {
        FirebaseAuth
            .getRedirectResult(auth)
            .then {
                // This gives you a Google Access Token. You can use it to access Google APIs.
                val credential = FirebaseAuth
                    .GoogleAuthProvider
                    .credentialFromResult(it);
                // TAG_LOGIN.log("accessToken: ${credential.accessToken}")

                auth.currentUser.emitUser()
            }.catch {
                TAG_LOGIN.error("loginWithGetRedirectResult")
                console.log(it)
                it
            }.await<JsAny>()
    }

    private suspend fun loginWithSignInWithPopup() {
        FirebaseAuth
            .signInWithPopup(auth, googleProvider)
            .then {
                // This gives you a Google Access Token. You can use it to access the Google API.
                val credential = FirebaseAuth
                    .GoogleAuthProvider
                    .credentialFromResult(it)
                // TAG_LOGIN.log("accessToken: ${credential.accessToken}")

                auth.currentUser.emitUser()
            }.catch {
                TAG_LOGIN.error("loginWithSignInWithPopup")
                console.log(it)
                it
            }.await<JsAny>()
    }

}
