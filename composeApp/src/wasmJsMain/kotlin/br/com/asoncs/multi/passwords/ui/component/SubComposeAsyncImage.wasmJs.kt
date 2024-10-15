package br.com.asoncs.multi.passwords.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import br.com.asoncs.multi.passwords.data.repository.ImageRepository
import br.com.asoncs.multi.passwords.extension.error
import br.com.asoncs.multi.passwords.extension.log
import br.com.asoncs.multi.passwords.ui.app.TAG_APP
import org.koin.compose.koinInject

@Composable
actual fun SubComposeAsyncImage(
    model: String?,
    contentDescription: String?,
    modifier: Modifier,
    contentScale: ContentScale,
    error: @Composable (() -> Unit)?,
    loading: @Composable (() -> Unit)?
) {
    val repository = koinInject<ImageRepository>()
    var state: ImageState by remember {
        mutableStateOf(ImageState.Loading)
    }

    Box(
        modifier
    ) {
        state.let { state ->
            when (state) {
                ImageState.Error -> error
                    ?.invoke()

                ImageState.Loading -> loading
                    ?.invoke()

                is ImageState.Success -> {
                    Image(
                        state.image,
                        contentDescription,
                        Modifier
                            .matchParentSize(),
                        contentScale = contentScale
                    )
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        runCatching {
            TAG_APP.log("TODO Fix loading images on web.")
            repository
                .getImage(model)
                ?: throw IllegalStateException("Image is null")
        }.onFailure {
            TAG_APP.error("SubComposeAsyncImage.getImage", it)
            state = ImageState.Error
        }.onSuccess {
            state = ImageState.Success(it)
        }
    }
}

private sealed class ImageState {
    data object Error : ImageState()
    data object Loading : ImageState()
    data class Success(
        val image: ImageBitmap
    ) : ImageState()
}
