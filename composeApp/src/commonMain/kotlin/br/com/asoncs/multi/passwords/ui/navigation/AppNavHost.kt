package br.com.asoncs.multi.passwords.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.asoncs.multi.passwords.ui.home.HomeDestination
import br.com.asoncs.multi.passwords.ui.home.homeDestination
import br.com.asoncs.multi.passwords.ui.splash.SplashDestination
import br.com.asoncs.multi.passwords.ui.splash.splashDestination

abstract class AppDestination(
    val route: String
)

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SplashDestination.route,
        modifier = modifier
            .fillMaxSize()
    ) {
        homeDestination()
        loginNavDestination(
            navigateToHome = {
                navController.navigateTo(HomeDestination)
            }
        )
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
