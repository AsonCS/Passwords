package br.com.asoncs.multi.passwords.ui.scanner.ml

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.asoncs.multi.passwords.camera.CameraComponent
import br.com.asoncs.multi.passwords.ui.component.Loading
import br.com.asoncs.multi.passwords.ui.scanner.Analyzer
import br.com.asoncs.multi.passwords.ui.scanner.Result
import kotlinx.coroutines.launch

@Composable
internal actual fun PlatformScannerMLScreen(
    modifier: Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var isLoading by remember {
        mutableStateOf(false)
    }
    var results by remember {
        mutableStateOf(emptyList<Result>())
    }
    var useCamera by remember {
        mutableStateOf(false)
    }

    val pickMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            scope.launch {
                isLoading = true
                results = (Analyzer.analyze(context, uri) + results)
                    .distinct()
                isLoading = false
            }
        }
    }

    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        TextButton(
            onClick = {
                useCamera = !useCamera
            }
        ) {
            Text(
                if (useCamera)
                    "Use media picker"
                else
                    "Use camera"
            )
        }

        if (useCamera) {
            CameraComponent(
                analyzer = Analyzer {
                    results = (it + results).distinct()
                }
            )
        } else {
            TextButton(
                onClick = {
                    pickMedia.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
            ) {
                Text(
                    "Chose an image"
                )
            }
        }

        if (isLoading) {
            Loading()
        }

        Text(
            text = results
                .joinToString()
        )
    }
}
