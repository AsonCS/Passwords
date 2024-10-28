package br.com.asoncs.multi.passwords.ui._navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.asoncs.multi.passwords.auth.AuthState.*
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.splash.SplashDestination

abstract class AppDestination<Args>(
    val route: String
) {
    abstract operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    )
}

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
        HomeNavDestination(
            appViewModel,
            this
        )
        LoginNavDestination(
            appViewModel,
            this
        )
        SplashDestination(
            Unit,
            this
        )
    }

    LaunchedEffect(Unit) {
        appViewModel.stateAuth.collect {
            when (it) {
                is LoggedIn -> {
                    navController.popBackStack()
                    navController.navigate(HomeNavDestination.route)
                }

                LoggedOut -> {
                    navController.popBackStack()
                    navController.navigate(LoginNavDestination.route)
                }

                Unknown -> {
                    // Do nothing
                }
            }
        }
    }
}
