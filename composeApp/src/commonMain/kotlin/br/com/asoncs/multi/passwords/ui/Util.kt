package br.com.asoncs.multi.passwords.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PreviewContainer(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    MaterialTheme {
        content(modifier)
    }
}
