package br.com.asoncs.multi.passwords

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import br.com.asoncs.multi.passwords.auth.AuthMock
import br.com.asoncs.multi.passwords.ui.app.App
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App(
            /*WasmJsAuth, // */ AuthMock,
        )
    }
}
