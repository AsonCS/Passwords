package br.com.asoncs.multi.passwords.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
actual fun SubComposeAsyncImage(
    model: String?,
    contentDescription: String?,
    modifier: Modifier,
    contentScale: ContentScale,
    error: @Composable (() -> Unit)?,
    loading: @Composable (() -> Unit)?
) {
    DefaultSubComposeAsyncImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        error = error,
        loading = loading
    )
}
