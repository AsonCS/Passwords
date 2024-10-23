package br.com.asoncs.multi.passwords.ui.home

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal actual fun PlatformScannerButton(
    onClick: () -> Unit,
    modifier: Modifier
) {
    TextButton(onClick, modifier) {
        Text("Google code scanner")
    }
}
