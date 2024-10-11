package br.com.asoncs.multi.passwords.auth

import androidx.activity.ComponentActivity
import br.com.asoncs.multi.passwords.auth.AuthState.LoggedIn
import br.com.asoncs.multi.passwords.auth.AuthState.LoggedOut
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

interface AuthAndroid : Auth {

    val activity: ComponentActivity
    override val authState: MutableStateFlow<AuthState>

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
        } catch (t: Throwable) {
            throw AuthException.UnknownException(t)
        }
    }

    override suspend fun logout() {
        Firebase.auth.signOut()
        authState.emit(LoggedOut)
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

        authState.emit(
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
                ?: authState.emit(LoggedOut)
        }
    }

}
