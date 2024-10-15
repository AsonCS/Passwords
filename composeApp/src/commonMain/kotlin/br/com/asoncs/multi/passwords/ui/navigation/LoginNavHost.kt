package br.com.asoncs.multi.passwords.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.login.*

abstract class LoginDestination(
    val route: String
)

@Composable
fun LoginNavHost(
    appViewModel: AppViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LoginNavDestination.route,
        modifier = modifier
    ) {
        loginDestination(
            appViewModel,
            navigateToSignup = {
                navController.navigate(SignupDestination.route)
            }
        )
        signupDestination(
            appViewModel,
            navigateUp = {
                navController.navigateUp()
            }
        )
    }
}

data object LoginNavDestination : AppDestination("login")

fun NavGraphBuilder.loginNavDestination(
    appViewModel: AppViewModel
) {
    composable(route = LoginNavDestination.route) {
        LoginNavHost(appViewModel)
    }
}
