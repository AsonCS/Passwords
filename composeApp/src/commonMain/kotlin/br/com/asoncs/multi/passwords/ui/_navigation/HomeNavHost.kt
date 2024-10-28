package br.com.asoncs.multi.passwords.ui._navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.home.HomeScreenDestination
import br.com.asoncs.multi.passwords.ui.textRecognition.TextRecognition
import br.com.asoncs.multi.passwords.ui.user.UserDestination

abstract class HomeDestination<Args>(
    val route: String
) {
    abstract operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    )
}

@Composable
fun HomeNavHost(
    appViewModel: AppViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination.route,
        modifier = modifier
    ) {
        HomeScreenDestination(
            HomeScreenDestination.Args(
                appViewModel,
                navigateToScanner = {
                    navController.navigate(ScannerNavDestination.route)
                },
                navigateToTextRecognition = {
                    navController.navigate(TextRecognition.route)
                },
                navigateToUser = {
                    navController.navigate(UserDestination.route)
                }
            ),
            this
        )
        UserDestination(
            UserDestination.Args(
                appViewModel,
                navigateUp = navController::navigateUp
            ),
            this
        )
        ScannerNavDestination(
            ScannerNavDestination.Args(
                appViewModel,
                navigateUp = navController::navigateUp
            ),
            this
        )
        TextRecognition(
            TextRecognition.Args(
                appViewModel,
                navigateUp = navController::navigateUp
            ),
            this
        )
    }
}

data object HomeNavDestination : AppDestination<AppViewModel>(
    "home"
) {
    override operator fun invoke(
        args: AppViewModel,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            HomeNavHost(args)
        }
    }
}
