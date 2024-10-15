package br.com.asoncs.multi.passwords.auth

import androidx.credentials.*
import androidx.credentials.exceptions.*
import br.com.asoncs.multi.passwords.R
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

interface AuthAndroidV1 : AuthAndroid {

    override suspend fun loginWithGoogle() {
        // TAG_LOGIN.log("AuthAndroidV1.loginWithGoogle")
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
            .create(activity)
        val googleIdOption = GetSignInWithGoogleOption
            .Builder(activity.getString(R.string.firebase_default_web_client_id))
            .build()
        val request = GetCredentialRequest
            .Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            val result = credentialManager.getCredential(
                context = activity,
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

}
