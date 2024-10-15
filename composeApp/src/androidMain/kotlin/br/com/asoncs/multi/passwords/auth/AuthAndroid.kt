package br.com.asoncs.multi.passwords.auth

import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import br.com.asoncs.multi.passwords.auth.AuthState.LoggedIn
import br.com.asoncs.multi.passwords.auth.AuthState.LoggedOut
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

interface AuthAndroid : Auth {

    var emit: (AuthState) -> Unit

    val activity: ComponentActivity

    override fun onAuthInit(
        emit: (AuthState) -> Unit
    ) {
        this.emit = emit
        onAuthResume(activity.lifecycleScope)
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

    fun onAuthCreate() {
        Firebase.auth
    }

    fun onAuthResume(
        scope: CoroutineScope
    ) {
        scope.launch {
            Firebase.auth
                .currentUser
                ?.emitUser()
                ?: emit(LoggedOut)
        }
    }

}
