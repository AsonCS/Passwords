package br.com.asoncs.multi.passwords.ui.home

import androidx.lifecycle.viewModelScope
import br.com.asoncs.multi.passwords.auth.Auth
import br.com.asoncs.multi.passwords.data.TAG_DATA
import br.com.asoncs.multi.passwords.data.repository.TestRepository
import br.com.asoncs.multi.passwords.extension.log
import br.com.asoncs.multi.passwords.ui.home.HomeState.Error
import br.com.asoncs.multi.passwords.ui.home.HomeState.Success
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModelImpl(
    private val auth: Auth,
    private val repository: TestRepository
) : HomeViewModel() {

    private val _state = MutableStateFlow<HomeState>(
        HomeState.Loading
    )
    override val state = _state
        .asStateFlow()

    override fun logout() {
        viewModelScope.launch {
            auth.logout()
        }
    }

    override fun githubUser() {
        if (_state.value is Success)
            return

        viewModelScope.launch {
            runCatching {
                repository.githubUser("AsonCS")
            }.onSuccess { result ->
                TAG_DATA.log("githubUser: $result")
                _state.update {
                    Success(result)
                }
            }.onFailure { throwable ->
                _state.update {
                    Error(
                        message = throwable.message
                            ?: "Unknown error"
                    )
                }
            }
        }
    }

}
