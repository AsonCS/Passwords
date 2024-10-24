package br.com.asoncs.multi.passwords.ui.scanner.ml

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import br.com.asoncs.multi.passwords.scan.Camera

@Composable
internal actual fun PlatformScannerMLScreen(
    modifier: Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val camera = remember {
        Camera(context, lifecycleOwner)
    }

    val state by camera.state.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        camera.startCamera()
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

        Button(
            onClick = { camera.captureImage() }
        ) {
            Text(
                "Capture Image"
            )
        }

        Spacer(
            Modifier
                .height(38.dp)
        )
        AndroidView(
            { camera.previewView },
            Modifier
                .fillMaxWidth()
        )
        Spacer(
            Modifier
                .height(38.dp)
        )

        Text(
            text = state
                .joinToString()
        )
    }

    LaunchedEffect(Unit) {
        if (allPermissionsGranted(context)) {
            camera.startCamera()
        } else {
            launcher.launch(requiredPermissions)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            camera.shutdown()
        }
    }
}

private val requiredPermissions = mutableListOf(
    Manifest.permission.CAMERA,
    Manifest.permission.RECORD_AUDIO
).apply {
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
        add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
}.toTypedArray()

private fun allPermissionsGranted(
    context: Context
) = requiredPermissions.all {
    ContextCompat.checkSelfPermission(
        context,
        it
    ) == PackageManager.PERMISSION_GRANTED
}





