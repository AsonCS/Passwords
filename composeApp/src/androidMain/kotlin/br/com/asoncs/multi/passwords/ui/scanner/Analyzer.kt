package br.com.asoncs.multi.passwords.ui.scanner

import android.content.Context
import android.net.Uri
import br.com.asoncs.multi.passwords.camera.CameraAnalyzer
import br.com.asoncs.multi.passwords.extension.error
import br.com.asoncs.multi.passwords.ui.home.TAG_HOME
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode.FORMAT_UNKNOWN
import com.google.mlkit.vision.common.InputImage

internal class Analyzer(
    private val onResult: (List<Result>) -> Unit
) : CameraAnalyzer() {
    override fun analyze(
        image: InputImage
    ) = analyze(
        image,
        onResult
    )

    companion object {
        fun analyze(
            context: Context,
            uri: Uri,
            onResult: (List<Result>) -> Unit
        ) {
            runCatching {
                val image = InputImage.fromFilePath(context, uri)
                analyze(
                    image,
                    onResult
                ).addOnCompleteListener {
                    image.mediaImage?.close()
                }
            }.onFailure {
                TAG_HOME.error("Scanner.Analyzer", it)
            }.getOrNull()
        }

        fun analyze(
            image: InputImage,
            onResult: (List<Result>) -> Unit
        ): Task<*> = BarcodeScanning.getClient(
            BarcodeScannerOptions
                .Builder()
                .enableAllPotentialBarcodes()
                .build()
        ).process(image)
            .addOnSuccessListener { barcodes ->
                barcodes
                    .mapNotNull {
                        if (it.format != FORMAT_UNKNOWN)
                            Result.Success(it)
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
                        Result.Error(
                            "YourImageAnalyzer cancel"
                        )
                    )
                )
            }
            .addOnFailureListener {
                onResult(
                    listOf(
                        Result.Error(
                            it.message
                                ?: "YourImageAnalyzer failure"
                        )
                    )
                )
            }
    }

}
