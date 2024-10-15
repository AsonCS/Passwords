package br.com.asoncs.multi.passwords.ui.user

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.home.HomeViewModel
import br.com.asoncs.multi.passwords.ui.navigation.HomeDestination

data object UserDestination : HomeDestination<UserDestination.Args>(
    "user"
) {
    class Args(
        val appViewModel: AppViewModel,
        val homeViewModel: HomeViewModel,
        val navigateUp: () -> Unit
    )

    override fun destination(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            UserScreen(
                args.appViewModel,
                args.homeViewModel,
                args.navigateUp
            )
        }
    }
}
