package br.com.asoncs.multi.passwords.ui.login

import androidx.lifecycle.viewModelScope
import br.com.asoncs.multi.passwords.auth.Auth
import br.com.asoncs.multi.passwords.extension.error
import br.com.asoncs.multi.passwords.ui.login.LoginState.Filling
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModelImpl(
    private val auth: Auth,
    initialState: LoginState = Filling()
) : LoginViewModel() {

    private val _state = MutableStateFlow(initialState)
    override val state = _state.asStateFlow()

    override fun login() {
        viewModelScope.launch {
            emitLoading()
            runCatching {
                val username = auth.verifyUsername(
                    _state.value.username
                )
                val password = auth.verifyPassword(
                    _state.value.password
                )
                auth.login(
                    password = password,
                    username = username
                )
            }.onFailure { error ->
                emitFilling(error.message ?: "login")
                TAG_LOGIN.error("login", error)
            }
        }
    }

    override fun loginWithGoogle() {
        viewModelScope.launch {
            emitLoading()
            runCatching {
                auth.loginWithGoogle()
            }.onFailure { error ->
                emitFilling(error.message ?: "loginWithGoogle")
                TAG_LOGIN.error("loginWithGoogle", error)
            }
        }
    }

    override fun signup() {
        viewModelScope.launch {
            emitLoading()
            runCatching {
                val username = auth.verifyUsername(
                    _state.value.username
                )
                val password = auth.verifyPassword(
                    _state.value.password
                )
                auth.signup(
                    password = password,
                    username = username
                )
            }.onFailure { error ->
                emitFilling(error.message ?: "signup")
                TAG_LOGIN.error("signup", error)
            }
        }
    }

    override fun updatePassword(
        password: String
    ) {
        _state.update {
            (it as Filling).copy(
                password = password
            )
        }
    }

    override fun updateUsername(
        username: String
    ) {
        _state.update {
            (it as Filling).copy(
                username = username
            )
        }
    }

    private fun emitFilling(
        errorMessage: String
    ) {
        _state.update {
            Filling(
                errorMessage = errorMessage,
                username = it.username,
                password = it.password
            )
        }
    }

    private fun emitLoading() {
        _state.update {
            LoginState.Loading(
                password = it.password,
                username = it.username
            )
        }
    }

}
