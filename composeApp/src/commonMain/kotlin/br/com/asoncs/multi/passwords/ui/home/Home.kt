package br.com.asoncs.multi.passwords.ui.home

import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.core.model.GithubUser
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.home.HomeScreenDestination.Args
import br.com.asoncs.multi.passwords.ui._navigation.HomeDestination
import kotlinx.coroutines.flow.StateFlow

const val TAG_HOME = "passwords_app:home"

data object HomeScreenDestination : HomeDestination<Args>(
    "home"
) {
    class Args(
        val appViewModel: AppViewModel,
        val navigateToScanner: () -> Unit,
        val navigateToTextRecognition: () -> Unit,
        val navigateToUser: () -> Unit
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            HomeScreen(args)
        }
    }
}

internal class HomeProps(
    val image: Painter,
    val navigateToScanner: () -> Unit,
    val navigateToTextRecognition: () -> Unit
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

    open fun githubUser() {
        TODO("Not yet implemented")
    }

}
