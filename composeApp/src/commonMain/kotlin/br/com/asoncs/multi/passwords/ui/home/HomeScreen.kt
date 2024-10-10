package br.com.asoncs.multi.passwords.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.asoncs.multi.passwords.Greeting
import br.com.asoncs.multi.passwords.auth.Auth
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import passwords.composeapp.generated.resources.Res
import passwords.composeapp.generated.resources.compose_multiplatform

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    auth: Auth = koinInject()
) {
    val scope = rememberCoroutineScope()

    HomeScreen(
        modifier = modifier,
        props = HomeProps(
            image = painterResource(Res.drawable.compose_multiplatform),
            onLogout = {
                scope.launch {
                    auth.logout()
                }
            }
        )
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier,
    props: HomeProps,
    initialShowContent: Boolean = false
) {
    var showContent by remember {
        mutableStateOf(initialShowContent)
    }
    Column(
        modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            props.onLogout
        ) {
            Text("Log out")
        }
        Button(
            onClick = { showContent = !showContent }
        ) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            val greeting = remember {
                Greeting().greet()
            }
            Column(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    props.image,
                    null
                )
                Text(
                    "Compose: $greeting"
                )
            }
        }
    }
}
