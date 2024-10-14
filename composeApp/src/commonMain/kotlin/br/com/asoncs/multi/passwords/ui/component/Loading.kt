package br.com.asoncs.multi.passwords.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Loading(
    modifier: Modifier = Modifier,
    size: Dp = 100.dp
) {
    CircularProgressIndicator(
        modifier
            .size(size)
    )
}
