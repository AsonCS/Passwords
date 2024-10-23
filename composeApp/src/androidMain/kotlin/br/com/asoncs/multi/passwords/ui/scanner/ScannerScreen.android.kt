package br.com.asoncs.multi.passwords.ui.scanner

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.barcode.common.Barcode.FORMAT_QR_CODE
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
internal actual fun PlatformScannerScreen(
    modifier: Modifier
) {
    ScannerList(modifier)
}

private data class Result(
    val format: Int?,
    val url: String?,
    val wifi: String?,
    val rawValue: String?
) {
    override fun toString(): String {
        val format = if (format != null)
            "format: $format\n"
        else
            ""
        val url = if (url != null)
            "url: $url\n"
        else
            ""
        val wifi = if (wifi != null)
            "wifi: $wifi\n"
        else
            ""

        return "--- Result ---\n$format$url${wifi}rawValue: $rawValue\n\n"
    }
}

@Composable
fun ScannerList(
    modifier: Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var lastResults by remember {
        mutableStateOf("")
    }

    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        TextButton(
            onClick = {
                scope.launch {
                    lastResults += scanQrCode(context)
                        .toString()
                }
            }
        ) {
            Text(
                "Scan QR Code"
            )
        }

        TextButton(
            onClick = {
                scope.launch {
                    lastResults += scanAnything(context)
                        .toString()
                }
            }
        ) {
            Text(
                "Scan Anything",
                Modifier
                    .fillMaxWidth()
            )
        }

        Text(
            text = lastResults
        )
    }
}

private suspend fun scanAnything(
    context: Context
): Result {
    return GmsBarcodeScanning
        .getClient(
            context,
            GmsBarcodeScannerOptions
                .Builder()
                .enableAutoZoom()
                .build()
        )
        .startScan()
        .await()
        .result()
}

private suspend fun scanQrCode(
    context: Context
): Result {
    return GmsBarcodeScanning
        .getClient(
            context,
            GmsBarcodeScannerOptions
                .Builder()
                .setBarcodeFormats(FORMAT_QR_CODE)
                .enableAutoZoom()
                .build()
        )
        .startScan()
        .await()
        .result()
}

private fun Barcode.result(): Result = Result(
    format = format,
    url = url
        ?.url,
    wifi = wifi
        ?.let {
            "password: ${it.password}, ssid: ${it.ssid}, type: ${it.encryptionType}"
        },
    rawValue = rawValue
)
