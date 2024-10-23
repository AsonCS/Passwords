package br.com.asoncs.multi.passwords.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.navigation.ScannerNavDestination.Args
import br.com.asoncs.multi.passwords.ui.scanner.ScannerScreenDestination
import br.com.asoncs.multi.passwords.ui.scanner.ml.ScannerMLDestination

abstract class ScannerDestination<Args>(
    val route: String
) {
    abstract fun destination(
        args: Args,
        builder: NavGraphBuilder
    )
}

@Composable
fun ScannerNavHost(
    args: Args,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ScannerScreenDestination.route,
        modifier = modifier
    ) {
        ScannerScreenDestination.destination(
            ScannerScreenDestination.Args(
                args.appViewModel,
                navigateToScannerML = {
                    navController.navigate(ScannerMLDestination.route)
                },
                args.navigateUp
            ),
            this
        )
        ScannerMLDestination.destination(
            ScannerMLDestination.Args(
                args.appViewModel,
                navigateUp = navController::navigateUp
            ),
            this
        )
    }
}

data object ScannerNavDestination : HomeDestination<Args>(
    "scanner"
) {
    class Args(
        val appViewModel: AppViewModel,
        val navigateUp: () -> Unit,
    )

    override fun destination(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            ScannerNavHost(args)
        }
    }
}
