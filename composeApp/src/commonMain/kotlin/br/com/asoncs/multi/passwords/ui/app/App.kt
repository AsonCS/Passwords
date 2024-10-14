package br.com.asoncs.multi.passwords.ui.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.rememberNavController
import br.com.asoncs.multi.passwords.auth.Auth
import br.com.asoncs.multi.passwords.auth.AuthState
import br.com.asoncs.multi.passwords.di.koinApplication
import br.com.asoncs.multi.passwords.ui.home.HomeDestination
import br.com.asoncs.multi.passwords.ui.navigation.LoginNavDestination
import br.com.asoncs.multi.passwords.ui.navigation.navigateTo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel

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
            val viewModel = koinViewModel<AppViewModel>()

            AppScreen(
                modifier,
                navController,
                viewModel
            )

            LaunchedEffect(true) {
                auth.onEmit = viewModel::stateAuthUpdate
                viewModel.collectBackStack(
                    navController.currentBackStackEntryFlow
                )
                delay(1_000)
                viewModel.stateAuth.collect {
                    when (it) {
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

data class AppTopBarState(
    val backHandler: (() -> Unit)? = null,
    val hasBackButton: Boolean = false,
    val hasTopBar: Boolean = false
)

abstract class AppViewModel : ViewModel() {

    open val stateAuth: StateFlow<AuthState>
        get() = TODO("Not yet implemented")
    open val stateTopBar: StateFlow<AppTopBarState>
        get() = TODO("Not yet implemented")

    open fun collectBackStack(
        currentBackStackEntryFlow: Flow<NavBackStackEntry>
    ) {
        TODO("Not yet implemented")
    }

    open fun stateAuthUpdate(
        state: AuthState
    ) {
        TODO("Not yet implemented")
    }

    open fun stateTopBarUpdate(
        backHandler: (() -> Unit)? = null,
        hasBackButton: Boolean,
        hasTopBar: Boolean
    ) {
        TODO("Not yet implemented")
    }

}
