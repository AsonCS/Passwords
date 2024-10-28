package br.com.asoncs.multi.passwords.ui.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.login.SignupDestination.Args
import br.com.asoncs.multi.passwords.ui._navigation.LoginDestination

data object SignupDestination : LoginDestination<Args>(
    "signup"
) {
    class Args(
        val appViewModel: AppViewModel,
        val loginViewModel: LoginViewModel,
        val navigateUp: () -> Unit
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            SignupScreen(args)
        }
    }
}
