package br.com.asoncs.multi.passwords.ui.user

import androidx.lifecycle.viewModelScope
import br.com.asoncs.multi.passwords.auth.Auth
import br.com.asoncs.multi.passwords.auth.User
import br.com.asoncs.multi.passwords.extension.error
import br.com.asoncs.multi.passwords.ui.login.TAG_LOGIN
import br.com.asoncs.multi.passwords.ui.user.UserState.Filling
import br.com.asoncs.multi.passwords.ui.user.UserState.Loading
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModelImpl(
    private val auth: Auth,
    initialState: UserState = Loading()
) : UserViewModel() {

    private val _state = MutableStateFlow(
        initialState
    )
    override val state = _state
        .asStateFlow()

    private var initialUser: User? = null

    override fun initUserData(
        user: User
    ) {
        initialUser = user
        _state.update {
            Filling(
                user = user
            )
        }
    }

    override fun logout() {
        viewModelScope.launch {
            auth.logout()
        }
    }

    override fun reload() {
        viewModelScope.launch {
            auth.lookup().let {
                _state.update { _ ->
                    Filling(
                        user = it
                    )
                }
            }
        }
    }

    override fun save() {
        viewModelScope.launch {
            _state.update {
                Loading(
                    user = it.user
                )
            }

            runCatching {
                val displayName = auth.verifyDisplayName(
                    _state
                        .value
                        .user
                        ?.name
                )
                val photoUrl = auth.verifyPhotoUrl(
                    _state
                        .value
                        .user
                        ?.photoUrl
                )
                auth.update(
                    displayName = displayName,
                    photoUrl = photoUrl
                )
            }.onFailure { error ->
                _state.update {
                    Filling(
                        errorMessage = error.message
                            ?: "save",
                        user = it.user
                    )
                }
                TAG_LOGIN.error("save", error)
            }.onSuccess {
                initUserData(it)
            }
        }
    }

    override fun updatePhotoUrl(
        url: String
    ) {
        _state.update { state ->
            val user = state.user!!.copy(
                photoUrl = url.takeIf { it.isNotBlank() }
            )
            (state as Filling).copy(
                hasChanges = user != initialUser,
                user = user
            )
        }
    }

    override fun updateDisplayName(
        name: String
    ) {
        _state.update { state ->
            val user = state.user!!.copy(
                name = name.takeIf { it.isNotBlank() }
            )
            (state as Filling).copy(
                hasChanges = user != initialUser,
                user = user
            )
        }
    }

}
