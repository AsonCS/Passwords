package br.com.asoncs.multi.passwords.ui.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import br.com.asoncs.multi.passwords.auth.*
import br.com.asoncs.multi.passwords.di.koinApplication
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
                delay(1_000)
                auth.onAuthInit(appViewModel::stateAuthUpdate)
            }
        }
    }
}

data class AppTopBarState(
    val handlerBack: (() -> Unit)? = null,
    val handlerUser: (() -> Unit)? = null,
    val showTopBar: Boolean = handlerBack != null
            || handlerUser != null
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
        handlerBack: (() -> Unit)? = null,
        handlerUser: (() -> Unit)? = null
    ) {
        TODO("Not yet implemented")
    }

}
