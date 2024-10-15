package br.com.asoncs.multi.passwords.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncs.multi.passwords.Greeting
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.component.Loading
import br.com.asoncs.multi.passwords.ui.home.HomeState.*
import org.jetbrains.compose.resources.painterResource
import passwords.composeapp.generated.resources.Res
import passwords.composeapp.generated.resources.compose_multiplatform

@Composable
fun HomeScreen(
    appViewModel: AppViewModel,
    homeViewModel: HomeViewModel,
    navigateToUser: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by homeViewModel.state
        .collectAsState()

    HomeScreen(
        modifier = modifier,
        props = HomeProps(
            image = painterResource(Res.drawable.compose_multiplatform)
        ),
        state = state
    )

    LaunchedEffect(Unit) {
        homeViewModel.githubUser()
        appViewModel.stateTopBarUpdate(
            handlerUser = navigateToUser
        )
    }
}

@Composable
internal fun HomeScreen(
    modifier: Modifier,
    props: HomeProps,
    state: HomeState,
    initialShowContent: Boolean = false
) {
    var showContent by remember {
        mutableStateOf(initialShowContent)
    }
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(
                horizontal = 8.dp,
                vertical = 32.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state is Loading) {
            Loading()
            return
        }

        if (state is Error) {
            Text(
                "Error: ${state.message}",
                color = MaterialTheme.colors.error
            )
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
                if (state is Success) {
                    Text(
                        "Github result"
                    )
                    Text(
                        "Name: ${state.githubUser.name}"
                    )
                    Text(
                        "Login: ${state.githubUser.login}"
                    )
                    Text(
                        "Id: ${state.githubUser.id}"
                    )
                }
            }
        }
    }
}
