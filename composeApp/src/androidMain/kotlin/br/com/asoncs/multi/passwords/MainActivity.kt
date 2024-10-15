package br.com.asoncs.multi.passwords

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.asoncs.multi.passwords.auth.*
import br.com.asoncs.multi.passwords.ui.app.App

class MainActivity : ComponentActivity(),
    AuthAndroidV1,
    AuthAndroidV2 {

    override val activity = this
    override var emit: (AuthState) -> Unit = {}
    override val signInLauncher = signInLauncher()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        // TAG_APP.log("MainActivity.onCreate")
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

    /*
    override fun onStart() {
        TAG_APP.log("MainActivity.onStart")
        super.onStart()
    }

    override fun onResume() {
        TAG_APP.log("MainActivity.onResume")
        super.onResume()
        onAuthResume(lifecycleScope)
    }

    override fun onPause() {
        TAG_APP.log("MainActivity.onPause")
        super.onPause()
    }

    override fun onStop() {
        TAG_APP.log("MainActivity.onStop")
        super.onStop()
    }

    override fun onDestroy() {
        TAG_APP.log("MainActivity.onDestroy")
        super.onDestroy()
    }
    // */

    override suspend fun loginWithGoogle() {
        // TODO Check NoClassDefFoundError super<AuthAndroidV2>.loginWithGoogle()
        super<AuthAndroidV1>.loginWithGoogle()
    }

}
