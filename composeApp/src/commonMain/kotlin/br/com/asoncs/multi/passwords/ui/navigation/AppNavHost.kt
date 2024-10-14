package br.com.asoncs.multi.passwords.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.asoncs.multi.passwords.ui.home.homeDestination
import br.com.asoncs.multi.passwords.ui.splash.SplashDestination
import br.com.asoncs.multi.passwords.ui.splash.splashDestination

abstract class AppDestination(
    val route: String,
    val hasBackButton: Boolean = false,
    val hasTopBar: Boolean = false
)

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SplashDestination.route,
        modifier = modifier
    ) {
        homeDestination()
        loginNavDestination()
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
