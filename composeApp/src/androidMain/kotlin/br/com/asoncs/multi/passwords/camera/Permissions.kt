package br.com.asoncs.multi.passwords.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat

@Composable
internal fun Permissions(
    camera: Camera,
    context: Context
) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        camera.startCamera()
    }

    LaunchedEffect(Unit) {
        if (isAllPermissionsGranted(context)) {
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

private fun isAllPermissionsGranted(
    context: Context
) = requiredPermissions.all {
    ContextCompat.checkSelfPermission(
        context,
        it
    ) == PackageManager.PERMISSION_GRANTED
}

private val requiredPermissions = mutableListOf(
    Manifest.permission.CAMERA,
    Manifest.permission.RECORD_AUDIO
).apply {
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
        add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
}.toTypedArray()
