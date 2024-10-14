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
import br.com.asoncs.multi.passwords.auth.AuthState.LoggedIn
import br.com.asoncs.multi.passwords.auth.User
import br.com.asoncs.multi.passwords.extension.log
import br.com.asoncs.multi.passwords.ui.component.Loading
import coil3.compose.SubcomposeAsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = koinViewModel()
) {
//    val stateAuth by viewModel.stateAuth
//        .collectAsState()
    val stateTopBar by viewModel.stateTopBar
        .collectAsState()

    TAG_APP.log("$stateTopBar")
    if (stateTopBar.hasTopBar) {
        AppTopBar(
            hasBackButton = stateTopBar.hasBackButton,
            modifier = modifier,
            backHandler = /*stateTopBar.backHandler ?:*/ {},
            user = null/*(stateAuth as? LoggedIn)
                ?.user*/
        )
    }
}

@Composable
fun AppTopBar(
    hasBackButton: Boolean,
    modifier: Modifier,
    backHandler: () -> Unit,
    user: User?
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
        if (hasBackButton) {
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

        SubcomposeAsyncImage(
            user?.photoUrl,
            user?.name,
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