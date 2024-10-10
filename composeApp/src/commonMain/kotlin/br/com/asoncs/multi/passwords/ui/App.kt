package br.com.asoncs.multi.passwords.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import br.com.asoncs.multi.passwords.auth.Auth
import br.com.asoncs.multi.passwords.auth.AuthState
import br.com.asoncs.multi.passwords.di.koinApplication
import br.com.asoncs.multi.passwords.ui.home.HomeDestination
import br.com.asoncs.multi.passwords.ui.navigation.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

const val TAG_APP = "passwords_app"

@Composable
fun App(
    auth: Auth,
    modifier: Modifier = Modifier
) {
    KoinApplication({
        koinApplication(auth)
    }) {
        MaterialTheme {
            val navController = rememberNavController()
            val viewModel = koinInject<AppViewModel>()

            AppNavHost(
                modifier
                    .fillMaxSize(),
                navController
            )

            LaunchedEffect(true) {
                delay(1_000)
                viewModel.state.collect {
                    when (it.authState) {
                        is AuthState.LoggedIn -> {
                            navController.navigateTo(HomeDestination)
                        }

                        AuthState.LoggedOut -> {
                            navController.navigateTo(LoginNavDestination)
                        }

                        AuthState.Unknown -> {
                            // Do nothing
                        }
                    }
                }
            }
        }
    }
}

data class AppState(
    val authState: AuthState = AuthState.Unknown
)

abstract class AppViewModel : ViewModel() {

    open val state: StateFlow<AppState>
        get() = TODO("Not yet implemented")

}
