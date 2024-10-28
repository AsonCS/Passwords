package br.com.asoncs.multi.passwords.ui._navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.login.*
import org.koin.compose.viewmodel.koinViewModel

abstract class LoginDestination<Args>(
    val route: String
) {
    abstract operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    )
}

@Composable
fun LoginNavHost(
    appViewModel: AppViewModel,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LoginScreenDestination.route,
        modifier = modifier
    ) {
        LoginScreenDestination(
            LoginScreenDestination.Args(
                appViewModel,
                loginViewModel,
                navigateToSignup = {
                    navController.navigate(SignupDestination.route)
                }
            ),
            this
        )
        SignupDestination(
            SignupDestination.Args(
                appViewModel,
                loginViewModel,
                navigateUp = navController::navigateUp
            ),
            this
        )
    }
}

data object LoginNavDestination : AppDestination<AppViewModel>(
    "login"
) {
    override operator fun invoke(
        args: AppViewModel,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            LoginNavHost(args)
        }
    }
}
