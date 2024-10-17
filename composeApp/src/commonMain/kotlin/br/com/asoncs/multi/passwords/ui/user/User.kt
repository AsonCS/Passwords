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

    abstract val hasChanges: Boolean
    abstract val user: User?

    data class Filling(
        val errorMessage: String? = null,
        override val hasChanges: Boolean = false,
        override val user: User? = null
    ) : UserState()

    class Loading(
        override val hasChanges: Boolean = false,
        override val user: User? = null
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

    open fun reload() {
        TODO("Not yet implemented")
    }

    open fun save() {
        TODO("Not yet implemented")
    }

    open fun updatePhotoUrl(
        url: String
    ) {
        TODO("Not yet implemented")
    }

    open fun updateDisplayName(
        name: String
    ) {
        TODO("Not yet implemented")
    }

}
