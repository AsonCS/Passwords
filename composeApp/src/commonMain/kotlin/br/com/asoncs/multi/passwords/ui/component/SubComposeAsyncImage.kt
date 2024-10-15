package br.com.asoncs.multi.passwords.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage

@Composable
expect fun SubComposeAsyncImage(
    model: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    error: @Composable (() -> Unit)? = null,
    loading: @Composable (() -> Unit)? = null
)

@Composable
fun DefaultSubComposeAsyncImage(
    model: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    error: @Composable (() -> Unit)? = null,
    loading: @Composable (() -> Unit)? = null
) {
    SubcomposeAsyncImage(
        model,
        contentDescription,
        modifier,
        contentScale = contentScale,
        error = {
            error
                ?.invoke()
        },
        loading = {
            loading
                ?.invoke()
        }
    )
}
