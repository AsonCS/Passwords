package br.com.asoncs.multi.passwords.ui.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.ui.navigation.LoginDestination

data object SignupDestination : LoginDestination("signup")

fun NavGraphBuilder.signupDestination(
    navigateUp: () -> Unit,
) {
    composable(route = SignupDestination.route) {
        SignupScreen(navigateUp)
    }
}
