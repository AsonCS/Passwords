package br.com.asoncs.multi.passwords

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import br.com.asoncs.multi.passwords.auth.*
import br.com.asoncs.multi.passwords.ui.app.App

class MainActivity : ComponentActivity(),
    AuthAndroidV1,
    AuthAndroidV2 {

    override val activity = this
    override var emit: (AuthState) -> Unit = {}
    override val signInLauncher = signInLauncher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onAuthCreate()

        @Suppress("DEPRECATION")
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            App(
                auth = /*this, // */ AuthMock,
            )
        }
    }

    override fun onResume() {
        super.onResume()
        onAuthResume(lifecycleScope)
    }

    override suspend fun loginWithGoogle() {
        // TODO Check NoClassDefFoundError super<AuthAndroidV2>.loginWithGoogle()
        super<AuthAndroidV1>.loginWithGoogle()
    }

}
