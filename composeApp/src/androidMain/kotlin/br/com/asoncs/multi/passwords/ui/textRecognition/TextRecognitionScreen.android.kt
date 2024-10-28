package br.com.asoncs.multi.passwords.ui.textRecognition

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

@Composable
internal actual fun PlatformTextRecognitionScreen(
    modifier: Modifier
) {
    TextRecognitionScreen(modifier)
}

@Composable
fun TextRecognitionScreen(
    modifier: Modifier
) {
    var results by remember {
        mutableStateOf(emptyList<String>())
    }
    var useJapanese by remember {
        mutableStateOf(false)
    }

    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        TextButton(
            onClick = {
                useJapanese = !useJapanese
            }
        ) {
            Text(
                text = if (useJapanese)
                    "Use default"
                else
                    "Use Japanese"
            )
        }

        CameraComponent(
            analyzer = Analyzer(
                useJapanese = { useJapanese }
            ) { result ->
                results = (listOf(result) + results)
                    .take(30)
                    .distinctBy {
                        it.lowercase()
                            .replace("\n", "")
                            .replace("\t", "")
                            .replace("\r", "")
                            .trim()
                    }
            }
        )

        Text(
            text = results
                .joinToString()
        )
    }
}
