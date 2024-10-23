package br.com.asoncs.multi.passwords.ui.scanner.ml

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import br.com.asoncs.multi.passwords.extension.error
import br.com.asoncs.multi.passwords.ui.home.TAG_HOME
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.common.Barcode.FORMAT_QR_CODE

@Composable
internal actual fun PlatformScannerMLScreen(
    modifier: Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = remember {
        PreviewView(context)
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        startCamera(
            context,
            lifecycleOwner,
            previewView
        )
    }

    Column(
        modifier
    ) {
        Text(
            "ML Scanner"
        )
        AndroidView(
            { previewView },
            Modifier
                .fillMaxSize()
        )
    }

    /*
    // Set up the listeners for take photo and video capture buttons
    viewBinding.imageCaptureButton.setOnClickListener { takePhoto() }
    viewBinding.videoCaptureButton.setOnClickListener { captureVideo() }

    cameraExecutor = Executors.newSingleThreadExecutor()
     */

    LaunchedEffect(Unit) {
        if (allPermissionsGranted(context)) {
            startCamera(
                context,
                lifecycleOwner,
                previewView
            )
        } else {
            launcher.launch(requiredPermissions)
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

private fun startCamera(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView
) {
    val cameraProviderFuture = ProcessCameraProvider
        .getInstance(context)

    cameraProviderFuture.addListener({
        // Used to bind the lifecycle of cameras to the lifecycle owner
        val cameraProvider: ProcessCameraProvider = cameraProviderFuture
            .get()

        // Preview
        val preview = Preview.Builder()
            .build()
            .also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

        // Select back camera as a default
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        try {
            // Unbind use cases before rebinding
            cameraProvider.unbindAll()

            // Bind use cases to camera
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview
            )

        } catch (t: Throwable) {
            TAG_HOME.error("Use case binding failed", t)
        }

    }, ContextCompat.getMainExecutor(context))
}

private suspend fun scan() {
    val options = BarcodeScannerOptions
        .Builder()
        .setBarcodeFormats(
            FORMAT_QR_CODE
        )
        .enableAllPotentialBarcodes()
        .build()
}

private class YourImageAnalyzer : ImageAnalysis.Analyzer {
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(
        imageProxy: ImageProxy
    ) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            // val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            // Pass image to an ML Kit Vision API
            // ...
        }
    }
}