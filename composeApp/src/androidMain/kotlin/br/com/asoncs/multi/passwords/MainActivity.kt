package br.com.asoncs.multi.passwords

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import br.com.asoncs.multi.passwords.auth.AuthAndroid
import br.com.asoncs.multi.passwords.auth.AuthState
import br.com.asoncs.multi.passwords.ui.App
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity(), AuthAndroid {

    override val context = this

    override val authState = MutableStateFlow<AuthState>(AuthState.Unknown)

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

}
