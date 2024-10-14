package br.com.asoncs.multi.passwords.ui.home

import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.core.model.GithubUser
import br.com.asoncs.multi.passwords.ui.navigation.AppDestination
import kotlinx.coroutines.flow.StateFlow

data object HomeDestination : AppDestination(
    hasTopBar = true,
    route = "home"
)

internal class HomeProps(
    val image: Painter,
    val onLogout: () -> Unit
)

sealed class HomeState {

    data class Error(
        val message: String
    ) : HomeState()

    data object Loading : HomeState()

    data class Success(
        val githubUser: GithubUser
    ) : HomeState()

}

abstract class HomeViewModel : ViewModel() {

    open val state: StateFlow<HomeState>
        get() = TODO("Not yet implemented")

    open fun logout() {
        TODO("Not yet implemented")
    }

    open fun githubUser() {
        TODO("Not yet implemented")
    }

}

fun NavGraphBuilder.homeDestination() {
    composable(route = HomeDestination.route) {
        HomeScreen()
    }
}
