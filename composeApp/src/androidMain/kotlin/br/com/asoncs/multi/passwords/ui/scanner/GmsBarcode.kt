package br.com.asoncs.multi.passwords.ui.scanner

import android.content.Context
import com.google.mlkit.vision.barcode.common.Barcode.BarcodeFormat
import com.google.mlkit.vision.barcode.common.Barcode.FORMAT_QR_CODE
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

internal class GmsBarcode(
    private val context: Context,
    private val onResult: (Result) -> Unit
) {
    fun scan(
        @BarcodeFormat format: Int? = null,
        @BarcodeFormat vararg moreFormats: Int = intArrayOf(),
    ) {
        GmsBarcodeScanning
            .getClient(
                context,
                GmsBarcodeScannerOptions
                    .Builder()
                    .apply {
                        if (format != null) {
                            setBarcodeFormats(format, *moreFormats)
                        }
                    }
                    .enableAutoZoom()
                    .build()
            )
            .startScan()
            .addOnSuccessListener { barcode ->
                onResult(
                    Result.Success(barcode)
                )
            }
            .addOnFailureListener { exception ->
                onResult(
                    Result.Error(
                        exception.message
                            ?: "scan failed"
                    )
                )
            }
            .addOnCanceledListener {
                onResult(
                    Result.Error(
                        "scan cancel"
                    )
                )
            }
    }

    fun scanQrCode() {
        scan(FORMAT_QR_CODE)
    }
}
