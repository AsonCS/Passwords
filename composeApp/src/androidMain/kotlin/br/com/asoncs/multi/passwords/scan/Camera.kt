package br.com.asoncs.multi.passwords.scan

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import br.com.asoncs.multi.passwords.extension.error
import br.com.asoncs.multi.passwords.ui.home.TAG_HOME
import io.ktor.util.date.getTimeMillis
import kotlinx.coroutines.flow.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Camera(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner,
    private val withAnalyzer: Boolean = true
) {

    private val _state = MutableStateFlow<List<ScanResult>>(
        emptyList()
    )
    val state = _state.asStateFlow()

    private val executor by lazy {
        Executors.newSingleThreadExecutor()
    }
    private val imageCapture by lazy {
        ImageCapture.Builder().build()
    }

    val previewView by lazy {
        PreviewView(context)
    }

    fun captureImage() {
        val name = "CameraxImage_${getTimeMillis()}.jpeg"
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        val outputOptions = ImageCapture
            .OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        imageCapture.takePicture(
            outputOptions,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                }

                override fun onError(exception: ImageCaptureException) {
                    TAG_HOME.error("imageCapture.takePicture: Failed", exception)
                }

            }
        )
    }

    fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider
            .getInstance(context)

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider = cameraProviderFuture
                .get()

            val useCases = mutableListOf<UseCase>().apply {
                add(
                    Preview
                        .Builder()
                        .build()
                        .also {
                            it.setSurfaceProvider(previewView.surfaceProvider)
                        }
                )
                add(imageCapture)
                if (withAnalyzer) {
                    add(getImageAnalysis(executor))
                }
            }.toTypedArray()

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.DEFAULT_BACK_CAMERA, // Select back camera as a default
                    *useCases
                )

            } catch (t: Throwable) {
                TAG_HOME.error("Use case binding failed", t)
            }

        }, ContextCompat.getMainExecutor(context))
    }

    fun shutdown() {
        executor.shutdown()
    }

    private fun getImageAnalysis(
        executor: ExecutorService
    ): ImageAnalysis {
        return ImageAnalysis.Builder()
            // enable the following line if RGBA output is needed.
            /*.setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
            .setTargetResolution(Size(1280, 720))
            .setResolutionSelector(
                ResolutionSelector.Builder()
                    .setResolutionFilter { supportedSizes, _ ->
                        listOf(supportedSizes.minBy { it.width })
                    }
                    .build()
            )*/
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also { analysis ->
                analysis.setAnalyzer(
                    executor,
                    CameraAnalyzer { results ->
                        _state.update {
                            (results + it).distinct()
                        }
                    }
                )
            }
    }

}
