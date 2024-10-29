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
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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
        suspend fun analyze(
            context: Context,
            uri: Uri
        ): List<Result> {
            return withContext(IO) {
                val image = runCatching {
                    InputImage.fromFilePath(context, uri)
                }.onFailure {
                    TAG_HOME.error("Scanner.Analyzer", it)
                }.getOrNull()
                    ?: return@withContext emptyList()

                suspendCoroutine { continuation ->
                    var resumed = false
                    analyze(
                        image,
                        onResult = {
                            continuation.resume(it)
                            resumed = true
                        }
                    ).addOnCompleteListener {
                        image.mediaImage?.close()
                        if (resumed.not()) {
                            continuation.resume(emptyList())
                        }
                    }
                }
            }
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
