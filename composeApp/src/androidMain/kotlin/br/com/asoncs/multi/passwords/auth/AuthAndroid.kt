package br.com.asoncs.multi.passwords.auth

import android.content.Context
import androidx.credentials.*
import androidx.credentials.exceptions.*
import br.com.asoncs.multi.passwords.R
import br.com.asoncs.multi.passwords.auth.AuthState.LoggedIn
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

interface AuthAndroid : Auth {

    override val authState: MutableStateFlow<AuthState>

    val context: Context

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

    override suspend fun loginWithGoogle() {
        /*
        delay(3_000)
        authState.emit(
            LoggedIn(
                User(
                    "Son",
                    "abc@com.br",
                    null,
                    "uid"
                )
            )
        )
        return
        // */

        val auth = Firebase.auth

        val credentialManager = CredentialManager
            .create(context)
        val googleIdOption = GetSignInWithGoogleOption
            .Builder(context.getString(R.string.firebase_default_web_client_id))
            .build()
        val request = GetCredentialRequest
            .Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            val result = credentialManager.getCredential(
                context = context,
                request = request
            )

            val credential = result.credential

            when {
                credential is GoogleIdTokenCredential -> {
                    val authCredential = GoogleAuthProvider
                        .getCredential(
                            credential.idToken,
                            null
                        )
                    auth.signInWithCredential(authCredential)
                        .await()
                        .user
                        .emitUser()
                }

                credential is CustomCredential
                        && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
                    val googleIdTokenCredential = GoogleIdTokenCredential
                        .createFrom(credential.data)
                    val authCredential = GoogleAuthProvider
                        .getCredential(
                            googleIdTokenCredential.idToken,
                            null
                        )
                    auth
                        .signInWithCredential(authCredential)
                        .await()
                        .user
                        .emitUser()
                }

                else -> {
                    throw AuthException.UnknownException()
                }
            }
        } catch (e: NoCredentialException) {
            throw AuthException.NoCredentialException(e)
        } catch (e: GetCredentialCancellationException) {
            throw AuthException.GetCredentialCancellationException(e)
        } catch (e: GetCredentialException) {
            throw AuthException.GetCredentialException(e)
        } catch (t: Throwable) {
            throw AuthException.UnknownException(t)
        }
    }

    override suspend fun logout() {
        Firebase.auth.signOut()
        authState.emit(AuthState.LoggedOut)
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
                ?: authState.emit(AuthState.LoggedOut)
        }
    }

    private suspend fun FirebaseUser?.emitUser() {
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

}
