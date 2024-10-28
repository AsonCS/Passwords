package br.com.asoncs.multi.passwords.camera

import androidx.annotation.OptIn
import androidx.camera.core.*
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage

abstract class CameraAnalyzer : ImageAnalysis.Analyzer {
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(
        imageProxy: ImageProxy
    ) {
        val mediaImage = imageProxy.image
            ?: let {
                imageProxy.close()
                return
            }

        analyze(
            InputImage
                .fromMediaImage(
                    mediaImage,
                    imageProxy.imageInfo.rotationDegrees
                )
        ).addOnCompleteListener {
            imageProxy.close()
        }
    }

    abstract fun analyze(
        image: InputImage
    ): Task<*>
}
