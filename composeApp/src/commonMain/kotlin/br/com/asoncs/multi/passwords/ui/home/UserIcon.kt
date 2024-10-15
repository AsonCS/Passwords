package br.com.asoncs.multi.passwords.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.asoncs.multi.passwords.ui.component.Loading
import coil3.compose.SubcomposeAsyncImage

@Composable
fun UserIcon(
    size: Dp,
    userName: String?,
    userPhotoUrl: String?,
    modifier: Modifier = Modifier
) {
    val borderSize = size / 12

    SubcomposeAsyncImage(
        userPhotoUrl,
        userName,
        modifier
            .size(size)
            .padding(4.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
        error = {
            Icon(
                Icons.Sharp.Person,
                userName,
                Modifier
                    .border(borderSize, Color.Black, CircleShape)
                    .padding(borderSize)
            )
        },
        loading = {
            Loading(
                size = size
            )
        }
    )
}