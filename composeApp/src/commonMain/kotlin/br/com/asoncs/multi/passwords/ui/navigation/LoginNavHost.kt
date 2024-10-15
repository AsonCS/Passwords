package br.com.asoncs.multi.passwords.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.login.LoginScreenDestination
import br.com.asoncs.multi.passwords.ui.login.SignupDestination

abstract class LoginDestination<Args>(
    val route: String
) {
    abstract fun destination(
        args: Args,
        builder: NavGraphBuilder
    )
}

@Composable
fun LoginNavHost(
    appViewModel: AppViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LoginScreenDestination.route,
        modifier = modifier
    ) {
        LoginScreenDestination.destination(
            LoginScreenDestination.Args(
                appViewModel,
                navigateToSignup = {
                    navController.navigate(SignupDestination.route)
                }
            ),
            this
        )
        SignupDestination.destination(
            SignupDestination.Args(
                appViewModel,
                navigateUp = navController::navigateUp
            ),
            this
        )
    }
}

data object LoginNavDestination : AppDestination<AppViewModel>(
    "login"
) {
    override fun destination(
        args: AppViewModel,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            LoginNavHost(args)
        }
    }
}
