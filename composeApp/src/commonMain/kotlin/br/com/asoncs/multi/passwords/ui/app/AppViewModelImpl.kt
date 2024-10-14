package br.com.asoncs.multi.passwords.ui.app

import androidx.navigation.NavBackStackEntry
import br.com.asoncs.multi.passwords.auth.AuthState
import br.com.asoncs.multi.passwords.extension.log
import kotlinx.coroutines.flow.*

class AppViewModelImpl : AppViewModel() {

    private val _stateAuth: MutableStateFlow<AuthState> = MutableStateFlow(
        AuthState.Unknown
    )
    override val stateAuth = _stateAuth
        .asStateFlow()

    private val _stateTopBar = MutableStateFlow(
        AppTopBarState()
    )
    override val stateTopBar = _stateTopBar
        .asStateFlow()

    override fun collectBackStack(
        currentBackStackEntryFlow: Flow<NavBackStackEntry>
    ) {
//        viewModelScope.launch {
//            currentBackStackEntryFlow.collect { entry ->
//                val destination = when (entry.destination.route) {
//                    HomeDestination.route -> HomeDestination
//                    LoginNavDestination.route -> LoginNavDestination
//                    else -> SplashDestination
//                }
//                _stateTopBar.update {
//                    AppTopBarState(
//                        hasBackButton = destination.hasBackButton,
//                        hasTopBar = destination.hasTopBar
//                    )
//                }
//            }
//        }
    }

    override fun stateAuthUpdate(
        state: AuthState
    ) {
        _stateAuth.update {
            state
        }
    }

    override fun stateTopBarUpdate(
        backHandler: (() -> Unit)?,
        hasBackButton: Boolean,
        hasTopBar: Boolean
    ) {
        _stateTopBar.update {
            AppTopBarState(
                //backHandler = backHandler,
                hasBackButton = hasBackButton,
                hasTopBar = hasTopBar
            ).also {
                TAG_APP.log("stateTopBarUpdate: $it")
            }
        }
    }

}
