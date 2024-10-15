package br.com.asoncs.multi.passwords.ui.app

import br.com.asoncs.multi.passwords.auth.AuthState
import br.com.asoncs.multi.passwords.auth.AuthState.LoggedIn
import kotlinx.coroutines.flow.*

class AppViewModelImpl : AppViewModel() {

    private val _stateAuth: MutableStateFlow<AuthState> = MutableStateFlow(
        AuthState.Unknown
    )
    override val stateAuth = _stateAuth
        .asStateFlow()
    override val stateAuthUser = _stateAuth
        .map {
            (it as? LoggedIn)?.user
        }

    private val _stateTopBar = MutableStateFlow(
        AppTopBarState()
    )
    override val stateTopBar = _stateTopBar
        .asStateFlow()

    override fun stateAuthUpdate(
        state: AuthState
    ) {
        _stateAuth.update {
            state
        }
    }

    override fun stateTopBarUpdate(
        handlerBack: (() -> Unit)?,
        handlerUser: (() -> Unit)?
    ) {
        _stateTopBar.update {
            AppTopBarState(
                handlerBack = handlerBack,
                handlerUser = handlerUser
            )
        }
    }

}
