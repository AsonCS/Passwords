package br.com.asoncs.multi.passwords.ui.navigation

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
    abstract fun destination(
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
        HomeNavDestination.destination(
            appViewModel,
            this
        )
        LoginNavDestination.destination(
            appViewModel,
            this
        )
        SplashDestination.destination(
            Unit,
            this
        )
    }

    LaunchedEffect(Unit) {
        appViewModel.stateAuth.collect {
            when (it) {
                is LoggedIn -> {
                    navController.navigate(HomeNavDestination.route) {
                        popUpTo(LoginNavDestination.route) {
                            inclusive = true
                        }
                    }
                }

                LoggedOut -> {
                    navController.navigate(LoginNavDestination.route) {
                        popUpTo(SplashDestination.route) {
                            inclusive = true
                        }
                    }
                }

                Unknown -> {
                    // Do nothing
                }
            }
        }
    }
}
