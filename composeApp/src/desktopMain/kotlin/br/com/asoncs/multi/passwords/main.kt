package br.com.asoncs.multi.passwords

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Passwords",
    ) {
        App()
    }
}