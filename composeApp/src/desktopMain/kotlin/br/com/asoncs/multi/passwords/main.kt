package br.com.asoncs.multi.passwords

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import br.com.asoncs.multi.passwords.auth.AuthJvm
import br.com.asoncs.multi.passwords.ui.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "\uD83D\uDD10\uD83D\uDD10\uD83D\uDD10",
    ) {
        App(
            AuthJvm, // */ AuthMock
        )
    }
}
