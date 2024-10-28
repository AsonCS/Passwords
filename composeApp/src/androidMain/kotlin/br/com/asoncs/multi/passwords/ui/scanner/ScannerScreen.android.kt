package br.com.asoncs.multi.passwords.ui.scanner

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
internal actual fun PlatformScannerScreen(
    modifier: Modifier
) {
    ScannerList(modifier)
}


@Composable
fun ScannerList(
    modifier: Modifier
) {
    val context = LocalContext.current

    var results by remember {
        mutableStateOf(emptyList<Result>())
    }

    val scan = remember {
        GmsBarcode(context) {
            results = (listOf(it) + results).distinct()
        }
    }

    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        TextButton(
            onClick = {
                scan.scanQrCode()
            }
        ) {
            Text(
                "Scan QR Code"
            )
        }

        TextButton(
            onClick = {
                scan.scan()
            }
        ) {
            Text(
                "Scan Anything",
                Modifier
                    .fillMaxWidth()
            )
        }

        Text(
            text = results
                .joinToString()
        )
    }
}
