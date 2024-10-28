package br.com.asoncs.multi.passwords.ui.textRecognition

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import br.com.asoncs.multi.passwords.ui.textRecognition.TextRecognition.Args

@Composable
internal expect fun PlatformTextRecognitionScreen(
    modifier: Modifier
)

@Composable
fun TextRecognitionScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    PlatformTextRecognitionScreen(
        modifier
    )

    LaunchedEffect(Unit) {
        args.appViewModel.stateTopBarUpdate(
            handlerBack = args.navigateUp
        )
    }
}
