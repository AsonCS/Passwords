package br.com.asoncs.multi.passwords.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.home.HomeScreenDestination
import br.com.asoncs.multi.passwords.ui.home.HomeViewModel
import br.com.asoncs.multi.passwords.ui.user.UserDestination
import org.koin.compose.viewmodel.koinViewModel

abstract class HomeDestination<Args>(
    val route: String
) {
    abstract fun destination(
        args: Args,
        builder: NavGraphBuilder
    )
}

@Composable
fun HomeNavHost(
    appViewModel: AppViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = koinViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination.route,
        modifier = modifier
    ) {
        HomeScreenDestination.destination(
            HomeScreenDestination.Args(
                appViewModel,
                homeViewModel,
                navigateToUser = {
                    navController.navigate(UserDestination.route)
                }
            ),
            this
        )
        UserDestination.destination(
            UserDestination.Args(
                appViewModel,
                homeViewModel,
                navigateUp = navController::navigateUp
            ),
            this
        )
    }
}

data object HomeNavDestination : AppDestination<AppViewModel>(
    "home"
) {
    override fun destination(
        args: AppViewModel,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            HomeNavHost(args)
        }
    }
}
