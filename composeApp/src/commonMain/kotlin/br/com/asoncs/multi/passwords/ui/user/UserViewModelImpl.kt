package br.com.asoncs.multi.passwords.ui.user

import androidx.lifecycle.viewModelScope
import br.com.asoncs.multi.passwords.auth.Auth
import br.com.asoncs.multi.passwords.auth.User
import br.com.asoncs.multi.passwords.ui.user.UserState.Filling
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModelImpl(
    private val auth: Auth,
    initialState: UserState = Filling()
) : UserViewModel() {

    private val _state = MutableStateFlow(
        initialState
    )
    override val state = _state
        .asStateFlow()

    private var displayName: String? = null
    private var photoUrl: String? = null

    override fun initUserData(
        user: User
    ) {
        displayName = user.name
        photoUrl = user.photoUrl
        _state.update {
            Filling(
                displayName = displayName,
                photoUrl = photoUrl
            )
        }
    }

    override fun logout() {
        viewModelScope.launch {
            auth.logout()
        }
    }

    override fun onUpdatePhotoUrl(
        url: String
    ) {
        val url = url.takeIf {
            it.isNotBlank()
        }
        _state.update {
            (it as Filling).copy(
                hasChanges = it.displayName != displayName
                        || url != photoUrl,
                photoUrl = url
            )
        }
    }

    override fun onUpdateDisplayName(
        name: String
    ) {
        val name = name.takeIf {
            it.isNotBlank()
        }
        _state.update {
            (it as Filling).copy(
                displayName = name,
                hasChanges = name != displayName
                        || it.photoUrl != photoUrl
            )
        }
    }

}
