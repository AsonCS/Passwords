package br.com.asoncs.multi.passwords.scan

import androidx.annotation.OptIn
import androidx.camera.core.*
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode.FORMAT_UNKNOWN
import com.google.mlkit.vision.common.InputImage

internal class CameraAnalyzer(
    private val onResult: (List<ScanResult>) -> Unit
) : ImageAnalysis.Analyzer {
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(
        imageProxy: ImageProxy
    ) {
        val mediaImage = imageProxy.image
            ?: let {
                imageProxy.close()
                return
            }

        val image = InputImage
            .fromMediaImage(
                mediaImage,
                imageProxy.imageInfo.rotationDegrees
            )

        BarcodeScanning.getClient(
            BarcodeScannerOptions
                .Builder()
                .enableAllPotentialBarcodes()
                .build()
        ).process(image)
            .addOnSuccessListener { barcodes ->
                barcodes
                    .mapNotNull {
                        if (it.format != FORMAT_UNKNOWN)
                            ScanResult.Success(it)
                        else null
                    }
                    .let {
                        if (it.isNotEmpty()) {
                            onResult(it)
                        }
                    }
            }
            .addOnCanceledListener {
                onResult(
                    listOf(
                        ScanResult.Error(
                            "YourImageAnalyzer cancel"
                        )
                    )
                )
            }
            .addOnFailureListener {
                onResult(
                    listOf(
                        ScanResult.Error(
                            it.message
                                ?: "YourImageAnalyzer failure"
                        )
                    )
                )
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
}
