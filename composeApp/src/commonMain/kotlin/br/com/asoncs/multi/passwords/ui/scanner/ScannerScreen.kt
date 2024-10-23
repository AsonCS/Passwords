package br.com.asoncs.multi.passwords.ui.scanner

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import br.com.asoncs.multi.passwords.ui.scanner.ScannerScreenDestination.Args

@Composable
internal expect fun PlatformScannerScreen(
    modifier: Modifier
)

@Composable
fun ScannerScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
    ) {
        TextButton(args.navigateToScannerML) {
            Text(
                "ML Scanner"
            )
        }

        PlatformScannerScreen(
            Modifier
        )
    }

    LaunchedEffect(Unit) {
        args.appViewModel.stateTopBarUpdate(
            handlerBack = args.navigateUp
        )
    }
}
