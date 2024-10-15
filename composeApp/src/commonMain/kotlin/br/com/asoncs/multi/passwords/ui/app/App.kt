package br.com.asoncs.multi.passwords.ui.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import br.com.asoncs.multi.passwords.auth.*
import br.com.asoncs.multi.passwords.auth.AuthState.*
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
            val appViewModel = koinViewModel<AppViewModel>()

            AppScreen(
                appViewModel,
                modifier,
                navController
            )

            LaunchedEffect(Unit) {
                auth.emit = appViewModel::stateAuthUpdate
                delay(1_000)
                appViewModel.stateAuth.collect {
                    when (it) {
                        is LoggedIn -> {
                            navController.navigateTo(HomeDestination)
                        }

                        LoggedOut -> {
                            navController.navigateTo(LoginNavDestination)
                        }

                        Unknown -> {
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
    val showUserIcon: Boolean = false,
    val showTopBar: Boolean = backHandler != null
            || showUserIcon
)

abstract class AppViewModel : ViewModel() {

    open val stateAuth: StateFlow<AuthState>
        get() = TODO("Not yet implemented")
    open val stateAuthUser: Flow<User?>
        get() = TODO("Not yet implemented")
    open val stateTopBar: StateFlow<AppTopBarState>
        get() = TODO("Not yet implemented")

    open fun stateAuthUpdate(
        state: AuthState
    ) {
        TODO("Not yet implemented")
    }

    open fun stateTopBarUpdate(
        backHandler: (() -> Unit)? = null,
        showUserIcon: Boolean = false
    ) {
        TODO("Not yet implemented")
    }

}
