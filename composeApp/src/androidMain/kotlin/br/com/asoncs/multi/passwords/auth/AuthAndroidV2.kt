package br.com.asoncs.multi.passwords.auth

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.lifecycleScope
import br.com.asoncs.multi.passwords.extension.error
import br.com.asoncs.multi.passwords.extension.log
import br.com.asoncs.multi.passwords.ui.login.TAG_LOGIN
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

interface AuthAndroidV2 : AuthAndroid {

    val signInLauncher: ActivityResultLauncher<Intent>

    override suspend fun loginWithGoogle() {
        TAG_LOGIN.log("AuthAndroidV2.loginWithGoogle")
        // com.google.android.gms.auth.api.credentials.CredentialsOptions
        // Choose authentication providers
        val providers = arrayListOf(
            // AuthUI.IdpConfig.EmailBuilder().build(),
            // AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            // AuthUI.IdpConfig.FacebookBuilder().build(),
            // AuthUI.IdpConfig.TwitterBuilder().build(),
        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI
            .getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        signInLauncher.launch(signInIntent)
    }

    fun signInLauncher() = activity
        .registerForActivityResult(
            FirebaseAuthUIActivityResultContract(),
        ) { res ->
            onSignInResult(res)
        }

    private fun onSignInResult(
        result: FirebaseAuthUIAuthenticationResult
    ) {
        if (result.resultCode == RESULT_OK) {
            activity.lifecycleScope.launch {
                // Successfully signed in
                Firebase
                    .auth
                    .currentUser
                    .emitUser()
            }
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
            val error = result
                .idpResponse
                ?.error
            when (error?.errorCode) {
                else -> {
                    TAG_LOGIN.error("AuthAndroidV2.onSignInResult", error)
                    throw AuthException.UnknownException(error)
                }
            }
        }
    }

}
