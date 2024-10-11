package br.com.asoncs.multi.passwords

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import br.com.asoncs.multi.passwords.auth.*
import br.com.asoncs.multi.passwords.ui.App
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity(), AuthAndroidV1, AuthAndroidV2 {

    override val activity = this
    override val authState = MutableStateFlow<AuthState>(
        AuthState.Unknown
    )
    override val signInLauncher = signInLauncher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onAuthCreate()

        @Suppress("DEPRECATION")
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            App(this)
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
