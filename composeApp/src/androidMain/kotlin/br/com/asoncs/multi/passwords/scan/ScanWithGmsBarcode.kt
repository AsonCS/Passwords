package br.com.asoncs.multi.passwords.scan

import android.content.Context
import com.google.mlkit.vision.barcode.common.Barcode.BarcodeFormat
import com.google.mlkit.vision.barcode.common.Barcode.FORMAT_QR_CODE
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.flow.*

class ScanWithGmsBarcode(
    private val context: Context
) {

    private val _state = MutableStateFlow<List<ScanResult>>(
        emptyList()
    )
    val state = _state.asStateFlow()

    fun scan(
        @BarcodeFormat format: Int? = null
    ) {
        GmsBarcodeScanning
            .getClient(
                context,
                GmsBarcodeScannerOptions
                    .Builder()
                    .apply {
                        if (format != null) {
                            setBarcodeFormats(format)
                        }
                    }
                    .enableAutoZoom()
                    .build()
            )
            .startScan()
            .addOnSuccessListener { barcode ->
                _state.update {
                    listOf(
                        ScanResult.Success(barcode)
                    ) + it
                }
            }
            .addOnFailureListener { exception ->
                _state.update {
                    listOf(
                        ScanResult.Error(
                            exception.message
                                ?: "scanAnything failed"
                        )
                    ) + it
                }
            }
            .addOnCanceledListener {
                _state.update {
                    listOf(
                        ScanResult.Error(
                            "scanAnything cancel"
                        )
                    ) + it
                }
            }
    }

    fun scanQrCode() {
        scan(FORMAT_QR_CODE)
    }

}
