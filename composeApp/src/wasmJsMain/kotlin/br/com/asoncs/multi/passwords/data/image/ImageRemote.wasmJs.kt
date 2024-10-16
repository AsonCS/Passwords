package br.com.asoncs.multi.passwords.data.image

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image as SkiaImage

actual val ByteArray.asImageBitmap: ImageBitmap?
    get() = SkiaImage
        .makeFromEncoded(this)
        .toComposeImageBitmap()
