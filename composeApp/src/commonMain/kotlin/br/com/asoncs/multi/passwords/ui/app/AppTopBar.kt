package br.com.asoncs.multi.passwords.ui.app

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.sharp.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import br.com.asoncs.multi.passwords.ui.component.Loading
import coil3.compose.SubcomposeAsyncImage

@Composable
fun AppTopBar(
    appViewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val stateAuthUser by appViewModel.stateAuthUser
        .collectAsState(null)
    val stateTopBar by appViewModel.stateTopBar
        .collectAsState()

    if (stateTopBar.showTopBar) {
        AppTopBar(
            backHandler = stateTopBar.backHandler,
            modifier = modifier,
            showUserIcon = stateTopBar.showUserIcon,
            userName = stateAuthUser
                ?.name,
            userPhotoUrl = stateAuthUser
                ?.photoUrl
        )
    }
}

@Composable
fun AppTopBar(
    backHandler: (() -> Unit)?,
    modifier: Modifier,
    showUserIcon: Boolean,
    userName: String?,
    userPhotoUrl: String?
) {
    val size = 48.dp

    Row(
        modifier
            .padding(
                end = 8.dp,
                start = 8.dp,
                top = 16.dp
            ).height(size),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (backHandler != null) {
            IconButton(
                backHandler
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    null,
                    Modifier
                        .size(size)
                )
            }
        }

        Spacer(
            Modifier
                .weight(1f)
        )

        if (showUserIcon) {
            SubcomposeAsyncImage(
                userPhotoUrl,
                userName,
                Modifier
                    .size(size)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                error = {
                    Icon(
                        Icons.Sharp.Person,
                        contentDescription,
                        Modifier
                            .border(4.dp, Color.Black, CircleShape)
                            .padding(4.dp)
                    )
                },
                loading = {
                    Loading(
                        size = size
                    )
                }
            )
        }
    }
}