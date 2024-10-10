package br.com.asoncs.multi.passwords

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import br.com.asoncs.multi.passwords.auth.AuthMock
import br.com.asoncs.multi.passwords.extension.log
import br.com.asoncs.multi.passwords.external.*
import br.com.asoncs.multi.passwords.ui.App
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    console.log("main-tes")
    console.log(firebaseConfig)
    val app = FirebaseApp.initializeApp(
        firebaseConfig
    )
    console.log(app)
    val analytics = FirebaseAnalytics.getAnalytics(app)
    console.log(app)
    console.log(analytics)
    ComposeViewport(document.body!!) {
        App(AuthMock)
    }
}
