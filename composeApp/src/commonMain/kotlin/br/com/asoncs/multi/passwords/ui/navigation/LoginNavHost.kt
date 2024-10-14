package br.com.asoncs.multi.passwords.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.login.*
import org.koin.compose.viewmodel.koinViewModel

abstract class LoginDestination(
    val route: String
)

@Composable
fun LoginNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModelApp: AppViewModel = koinViewModel()
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
            }
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

fun NavGraphBuilder.loginNavDestination() {
    composable(route = LoginNavDestination.route) {
        LoginNavHost()
    }
}
