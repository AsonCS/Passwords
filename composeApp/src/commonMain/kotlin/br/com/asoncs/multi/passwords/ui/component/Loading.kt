package br.com.asoncs.multi.passwords.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier
            .size(100.dp)
    )
}
