package br.com.asoncs.multi.passwords.ui

import androidx.lifecycle.viewModelScope
import br.com.asoncs.multi.passwords.auth.Auth
import br.com.asoncs.multi.passwords.extension.log
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AppViewModelImpl(
    auth: Auth
) : AppViewModel() {

    private val _state = MutableStateFlow(AppState())
    override val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            auth.authState.collect { authState ->
                TAG_APP.log("AuthState: $authState")
                _state.update { appState ->
                    appState.copy(
                        authState = authState
                    )
                }
            }
        }
    }

}
