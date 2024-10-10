package br.com.asoncs.multi.passwords.ui.login

import androidx.lifecycle.viewModelScope
import br.com.asoncs.multi.passwords.auth.Auth
import br.com.asoncs.multi.passwords.extension.error
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModelImpl(
    private val auth: Auth,
    initialState: LoginState = LoginState.Filling()
) : LoginViewModel() {

    private val _state = MutableStateFlow(initialState)
    override val state = _state.asStateFlow()

    override fun login() {
        viewModelScope.launch {
            // val user = auth.login()
        }
    }

    override fun loginWithGoogle() {
        viewModelScope.launch {
            _state.update {
                LoginState.Loading(
                    it.username,
                    it.password
                )
            }
            runCatching {
                auth.loginWithGoogle()
            }.onFailure { error ->
                _state.update {
                    LoginState.Filling(
                        errorMessage = error.message,
                        username = it.username,
                        password = it.password
                    )
                }
                TAG_LOGIN.error("loginWithGoogle", error)
            }
        }
    }

    override fun updatePassword(
        password: String
    ) {
        _state.update {
            LoginState.Filling(
                password = password,
                username = it.username
            )
        }
    }

    override fun updateUsername(
        username: String
    ) {
        _state.update {
            LoginState.Filling(
                password = it.password,
                username = username
            )
        }
    }

}
