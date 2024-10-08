package br.com.asoncs.multi.passwords

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.asoncs.multi.passwords.di.koinApplication
import br.com.asoncs.multi.passwords.ui.login.LoginScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import passwords.composeapp.generated.resources.Res
import passwords.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    KoinApplication(::koinApplication) {
        MaterialTheme {
            LoginScreen(
                Modifier
            )
        }
    }
}

@Preview
@Composable
private fun Screen(
    modifier: Modifier = Modifier
) {
    var showContent by remember {
        mutableStateOf(false)
    }
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { showContent = !showContent }) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            val greeting = remember {
                Greeting().greet()
            }
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(Res.drawable.compose_multiplatform),
                    null
                )
                Text(
                    "Compose: $greeting"
                )
            }
        }
    }
}
