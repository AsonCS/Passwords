package br.com.asoncs.multi.passwords.ui.login

import kotlinx.coroutines.flow.*

class LoginViewModelImpl(
    initialState: LoginState = LoginState()
) : LoginViewModel() {

    private val _state = MutableStateFlow(
        initialState.copy(
            updatePassword = ::updatePassword,
            updateUsername = ::updateUsername
        )
    )
    override val state = _state.asStateFlow()

    override fun updatePassword(
        password: String
    ) {
        _state.update {
            it.copy(password = password)
        }
    }

    override fun updateUsername(
        username: String
    ) {
        _state.update {
            it.copy(username = username)
        }
    }

}
