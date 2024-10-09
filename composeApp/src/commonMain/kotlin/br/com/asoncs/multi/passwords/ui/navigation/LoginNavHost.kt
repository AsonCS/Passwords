package br.com.asoncs.multi.passwords.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.asoncs.multi.passwords.ui.login.*

abstract class LoginDestination(
    val route: String
)

@Composable
fun LoginNavHost(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LoginNavDestination.route,
        modifier = modifier
    ) {
        loginDestination(
            navigateToHome = navigateToHome,
            navigateToSignup = {
                navController.navigateTo(SignupDestination)
            }
        )
        signupDestination(
            navigateToLogin = {
                navController.navigateTo(LoginScreenDestination)
            }
        )
    }
}

fun NavHostController.navigateTo(
    destination: LoginDestination
) {
    navigate(destination.route) {
        launchSingleTop = true
    }
}

data object LoginNavDestination : AppDestination("login")

fun NavGraphBuilder.loginNavDestination(
    navigateToHome: () -> Unit
) {
    composable(route = LoginNavDestination.route) {
        LoginNavHost(navigateToHome)
    }
}
