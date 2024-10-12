package br.com.asoncs.multi.passwords.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.asoncs.multi.passwords.Greeting
import br.com.asoncs.multi.passwords.ui.component.Loading
import br.com.asoncs.multi.passwords.ui.home.HomeState.*
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import passwords.composeapp.generated.resources.Res
import passwords.composeapp.generated.resources.compose_multiplatform

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinInject()
) {
    val state by viewModel.state
        .collectAsState()

    HomeScreen(
        modifier = modifier,
        props = HomeProps(
            image = painterResource(Res.drawable.compose_multiplatform),
            onLogout = viewModel::logout
        ),
        state = state
    )

    LaunchedEffect(true) {
        viewModel.githubUser()
    }
}

@Composable
internal fun HomeScreen(
    modifier: Modifier,
    props: HomeProps,
    state: HomeState,
    initialShowContent: Boolean = true
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
                state.user?.run {
                    Text(
                        "Hello $name, you're logged in!"
                    )
                    Text(
                        "Email: $email"
                    )
                }
                if (state is Success) {
                    Text(
                        "Github result"
                    )
                    // TODO Fix image, I think it is because of visibility
                    AsyncImage(
                        state.githubUser.avatarUrl,
                        contentDescription = state.githubUser.name,
                        Modifier
                            .size(200.dp)
                            .clip(CircleShape)
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
