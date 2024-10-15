package br.com.asoncs.multi.passwords.data.repository

import androidx.compose.ui.graphics.ImageBitmap
import br.com.asoncs.multi.passwords.data.remote.ImageRemote

expect val ByteArray.asImageBitmap: ImageBitmap?

interface ImageRepository {

    class Impl(
        private val remote: ImageRemote
    ) : ImageRepository {
        override suspend fun getImage(
            url: String?
        ): ImageBitmap? {
            return remote.getImage(url)
                ?.asImageBitmap
        }
    }

    suspend fun getImage(
        url: String?
    ): ImageBitmap?

}
