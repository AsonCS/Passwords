package br.com.asoncs.multi.passwords.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.home.homeDestination
import br.com.asoncs.multi.passwords.ui.splash.SplashDestination
import br.com.asoncs.multi.passwords.ui.splash.splashDestination

abstract class AppDestination(
    val route: String
)

@Composable
fun AppNavHost(
    appViewModel: AppViewModel,
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SplashDestination.route,
        modifier = modifier
    ) {
        homeDestination(appViewModel)
        loginNavDestination(appViewModel)
        splashDestination()
    }
}

fun NavHostController.navigateTo(
    destination: AppDestination
) {
    navigate(destination.route) {
        popBackStack()
    }
}
