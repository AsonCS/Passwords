package br.com.asoncs.multi.passwords.camera

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner

const val TAG_CAMERA = "passwords_app:camera"

@Composable
fun CameraComponent(
    modifier: Modifier = Modifier,
    analyzer: CameraAnalyzer? = null
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val camera = remember {
        Camera(
            analyzer,
            context,
            lifecycleOwner
        )
    }

    Permissions(camera, context)

    Column(
        modifier
            .padding(16.dp),
        verticalArrangement = Arrangement
            .spacedBy(38.dp)
    ) {
        AndroidView(
            { camera.previewView },
            Modifier
                .fillMaxWidth()
        )
        Button(
            onClick = { camera.captureImage() }
        ) {
            Text(
                "Capture"
            )
        }
    }
}
