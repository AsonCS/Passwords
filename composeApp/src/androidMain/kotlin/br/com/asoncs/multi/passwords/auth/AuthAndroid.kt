package br.com.asoncs.multi.passwords.auth

import androidx.activity.ComponentActivity
import br.com.asoncs.multi.passwords.auth.AuthState.LoggedIn
import br.com.asoncs.multi.passwords.auth.AuthState.LoggedOut
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

interface AuthAndroid : Auth {

    override var emit: (AuthState) -> Unit

    val activity: ComponentActivity

    override suspend fun getIdToken(): String? {
        return Firebase.auth
            .currentUser
            ?.getIdToken(false)
            ?.await()
            ?.token
    }

    override suspend fun onAuthInit(
        emit: (AuthState) -> Unit
    ) {
        this.emit = emit
        Firebase.auth
            .currentUser
            ?.emitUser()
            ?: emit(LoggedOut)
    }

    override suspend fun login(
        password: String,
        username: String
    ) {
        try {
            Firebase.auth
                .signInWithEmailAndPassword(username, password)
                .await()
                .user
                .emitUser()
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            throw AuthException.FirebaseAuthInvalidCredentialsException(e)
        } catch (t: Throwable) {
            throw AuthException.UnknownException(t)
        }
    }

    override suspend fun logout() {
        Firebase.auth.signOut()
        emit(LoggedOut)
    }

    override suspend fun signup(
        password: String,
        username: String
    ) {
        try {
            Firebase.auth
                .createUserWithEmailAndPassword(username, password)
                .await()
                .user
                .emitUser()
        } catch (e: FirebaseAuthWeakPasswordException) {
            throw AuthException.FirebaseAuthWeakPasswordException(e)
        } catch (t: Throwable) {
            throw AuthException.UnknownException(t)
        }
    }

    suspend fun FirebaseUser?.emitUser() {
        if (this == null)
            throw AuthException.InvalidUserException

        emit(
            LoggedIn(
                User(
                    name = displayName,
                    email = email,
                    photoUrl = photoUrl
                        ?.toString(),
                    uid = uid
                )
            )
        )
    }

}
