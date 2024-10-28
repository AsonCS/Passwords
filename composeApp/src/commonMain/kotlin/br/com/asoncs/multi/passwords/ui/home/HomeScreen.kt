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
import br.com.asoncs.multi.passwords.ui.component.Loading
import br.com.asoncs.multi.passwords.ui.home.HomeScreenDestination.Args
import br.com.asoncs.multi.passwords.ui.home.HomeState.*
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import passwords.composeapp.generated.resources.Res
import passwords.composeapp.generated.resources.compose_multiplatform

@Composable
internal expect fun PlatformScannerButton(
    onClick: () -> Unit,
    modifier: Modifier
)

@Composable
internal expect fun PlatformTextRecognitionButton(
    onClick: () -> Unit,
    modifier: Modifier
)

@Composable
fun HomeScreen(
    args: Args,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val state by homeViewModel.state
        .collectAsState()

    HomeScreen(
        modifier = modifier,
        props = HomeProps(
            image = painterResource(Res.drawable.compose_multiplatform),
            navigateToScanner = args.navigateToScanner,
            navigateToTextRecognition = args.navigateToTextRecognition
        ),
        state = state
    )

    LaunchedEffect(Unit) {
        homeViewModel.githubUser()
        args.appViewModel.stateTopBarUpdate(
            handlerUser = args.navigateToUser
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

        PlatformScannerButton(
            onClick = props.navigateToScanner,
            Modifier
        )

        PlatformTextRecognitionButton(
            onClick = props.navigateToTextRecognition,
            Modifier
        )

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
                    Text(
                        "ExampleCounter: ${state.githubUser.exampleCounter}"
                    )
                }
            }
        }
    }
}
