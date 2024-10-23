package br.com.asoncs.multi.passwords.ui.scanner.ml

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import br.com.asoncs.multi.passwords.ui.scanner.ml.ScannerMLDestination.Args

@Composable
internal expect fun PlatformScannerMLScreen(
    modifier: Modifier
)

@Composable
fun ScannerMLScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    PlatformScannerMLScreen(modifier)

    LaunchedEffect(Unit) {
        args.appViewModel.stateTopBarUpdate(
            handlerBack = args.navigateUp
        )
    }
}
