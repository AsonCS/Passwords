package br.com.asoncs.multi.passwords.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    viewModelApp: AppViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LoginNavDestination.route,
        modifier = modifier
    ) {
        loginDestination(
            navigateToSignup = {
                navController.navigate(SignupDestination.route)
            }
        )
        signupDestination(
            navigateUp = {
                navController.navigateUp()
            },
            viewModelApp
        )
    }

    LaunchedEffect(true) {
        viewModelApp.stateTopBarUpdate(null, false, true)
    }
}

data object LoginNavDestination : AppDestination(
    hasTopBar = true,
    route = "login"
)

fun NavGraphBuilder.loginNavDestination(
    viewModelApp: AppViewModel
) {
    composable(route = LoginNavDestination.route) {
        LoginNavHost(viewModelApp)
    }
}
