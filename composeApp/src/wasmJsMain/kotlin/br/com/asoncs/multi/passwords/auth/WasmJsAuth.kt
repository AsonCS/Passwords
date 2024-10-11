package br.com.asoncs.multi.passwords.auth

import br.com.asoncs.multi.passwords.auth.AuthState.LoggedOut
import br.com.asoncs.multi.passwords.extension.error
import br.com.asoncs.multi.passwords.extension.log
import br.com.asoncs.multi.passwords.external.*
import br.com.asoncs.multi.passwords.ui.login.TAG_LOGIN
import kotlinx.coroutines.await
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

object WasmJsAuth : Auth {

    override val authState = MutableStateFlow<AuthState>(
        AuthState.Unknown
    )

    private lateinit var app: JsAny
    private lateinit var auth: FirebaseAuth.Auth
    private lateinit var googleProvider: FirebaseAuth.GoogleAuthProvider

    suspend fun checkAuthState() {
        /*
        val currentUser = auth.currentUser
        console.log("checkAuthState", currentUser)
        if (currentUser != null)
            currentUser.emitUser()
        else
            authState.emit(LoggedOut)
        */
        authState.emit(LoggedOut)
        FirebaseAuth.onAuthStateChanged(auth) {
            val user = auth.currentUser
            console.log("checkAuthState.onAuthStateChanged", user)
            if (user != null)
                user.emitUser()
            else
                authState.update {
                    LoggedOut
                }
        }
    }

    fun init() {
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
    }

    override suspend fun loginWithGoogle() {
        TAG_LOGIN.log("loginWithGoogle")
        FirebaseAuth.signInWithPopup(auth, googleProvider)
            .then {
                // This gives you a Google Access Token. You can use it to access the Google API.
                val credential = FirebaseAuth
                    .GoogleAuthProvider
                    .credentialFromResult(it)
                TAG_LOGIN.log("accessToken: ${credential.accessToken}")

                auth.currentUser.emitUser()
            }
            .catch {
                TAG_LOGIN.error("loginWithGoogle")
                console.log(it)
                it
            }.await<JsAny>()
    }

    override suspend fun logout() {
        FirebaseAuth
            .signOut(auth)
            .await<JsAny>()
        authState.emit(LoggedOut)
    }

    override suspend fun signup(
        password: String,
        username: String
    ) {
        Here
    }

    private fun FirebaseAuth.User?.emitUser(): JsAny {
        if (this == null)
            throw AuthException.InvalidUserException

        authState.update {
            AuthState.LoggedIn(
                User(
                    name = displayName,
                    email = email,
                    photoUrl = photoURL,
                    uid = uid
                )
            )
        }

        return this
    }

}
