package br.com.asoncs.multi.passwords.ui.user

import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.auth.User
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.navigation.HomeDestination
import kotlinx.coroutines.flow.StateFlow

data object UserDestination : HomeDestination<UserDestination.Args>(
    "user"
) {
    class Args(
        val appViewModel: AppViewModel,
        val navigateUp: () -> Unit
    )

    override fun destination(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            UserScreen(
                args.appViewModel,
                args.navigateUp
            )
        }
    }
}

sealed class UserState {

    abstract val displayName: String?
    abstract val hasChanges: Boolean
    abstract val photoUrl: String?

    data class Filling(
        override val displayName: String? = null,
        override val hasChanges: Boolean = false,
        override val photoUrl: String? = null
    ) : UserState()

    class Loading(
        override val displayName: String?,
        override val hasChanges: Boolean = false,
        override val photoUrl: String?
    ) : UserState()

}

abstract class UserViewModel : ViewModel() {

    open val state: StateFlow<UserState>
        get() = TODO("Not yet implemented")

    open fun initUserData(
        user: User
    ) {
        TODO("Not yet implemented")
    }

    open fun logout() {
        TODO("Not yet implemented")
    }

    open fun onUpdatePhotoUrl(
        url: String
    ) {
        TODO("Not yet implemented")
    }

    open fun onUpdateDisplayName(
        name: String
    ) {
        TODO("Not yet implemented")
    }

}
