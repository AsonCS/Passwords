package br.com.asoncs.multi.passwords

import android.content.Context
import android.os.Bundle
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.asoncs.multi.passwords.auth.*
import br.com.asoncs.multi.passwords.ui.app.App
import org.koin.dsl.module

class MainActivity : ComponentActivity(),
    AuthAndroidV1,
    AuthAndroidV2 {

    override val activity = this
    override var emit: (AuthState) -> Unit = {}
    override val signInLauncher = signInLauncher()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        window.setSoftInputMode(SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            App(
                auth = this, // */ AuthMock,
                platformModule = module {
                    factory<Context> { this@MainActivity }
                }
            )
        }
    }

    override suspend fun loginWithGoogle() {
        // TODO Check NoClassDefFoundError super<AuthAndroidV2>.loginWithGoogle()
        super<AuthAndroidV1>.loginWithGoogle()
    }

}
