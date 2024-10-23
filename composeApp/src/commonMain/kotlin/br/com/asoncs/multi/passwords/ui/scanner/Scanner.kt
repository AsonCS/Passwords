package br.com.asoncs.multi.passwords.ui.scanner

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.navigation.ScannerDestination
import br.com.asoncs.multi.passwords.ui.scanner.ScannerScreenDestination.Args

data object ScannerScreenDestination : ScannerDestination<Args>(
    "scanner"
) {
    class Args(
        val appViewModel: AppViewModel,
        val navigateToScannerML: () -> Unit,
        val navigateUp: () -> Unit
    )

    override fun destination(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            ScannerScreen(args)
        }
    }
}