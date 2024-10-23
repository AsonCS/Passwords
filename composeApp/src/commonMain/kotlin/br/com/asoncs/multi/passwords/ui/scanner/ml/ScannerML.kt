package br.com.asoncs.multi.passwords.ui.scanner.ml

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.navigation.ScannerDestination
import br.com.asoncs.multi.passwords.ui.scanner.ml.ScannerMLDestination.Args

data object ScannerMLDestination : ScannerDestination<Args>(
    "scanner_ml"
) {
    class Args(
        val appViewModel: AppViewModel,
        val navigateUp: () -> Unit
    )

    override fun destination(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            ScannerMLScreen(args)
        }
    }
}
