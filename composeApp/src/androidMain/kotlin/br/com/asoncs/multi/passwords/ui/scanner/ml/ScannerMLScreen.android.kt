package br.com.asoncs.multi.passwords.ui.scanner.ml

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncs.multi.passwords.camera.CameraComponent
import br.com.asoncs.multi.passwords.ui.scanner.Analyzer
import br.com.asoncs.multi.passwords.ui.scanner.Result

@Composable
internal actual fun PlatformScannerMLScreen(
    modifier: Modifier
) {
    var results by remember {
        mutableStateOf(emptyList<Result>())
    }

    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        TextButton(
            onClick = {}
        ) {
            Text(
                "Not yet implemented, ML Scanner from media piker"
            )
        }

        CameraComponent(
            analyzer = Analyzer {
                results = (it + results).distinct()
            }
        )

        Text(
            text = results
                .joinToString()
        )
    }
}
