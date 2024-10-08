package br.com.asoncs.multi.passwords.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PreviewUtil(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    MaterialTheme {
        Box(
            modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier
                    .size(
                        height = 915.dp,
                        width = 412.dp
                    )
                    .background(Color.White)
            ) {
                content(Modifier)
            }
        }
    }
}
