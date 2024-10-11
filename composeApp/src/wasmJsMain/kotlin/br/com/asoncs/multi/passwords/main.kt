package br.com.asoncs.multi.passwords

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import br.com.asoncs.multi.passwords.auth.WasmJsAuth
import br.com.asoncs.multi.passwords.ui.App
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val auth = WasmJsAuth.apply {
            init()
        }

        App(auth)

        LaunchedEffect(true) {
            auth.checkAuthState()
        }
    }
}
