package br.com.asoncs.multi.passwords.ui.app

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncs.multi.passwords.ui.component.UserIcon

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
            handlerBack = stateTopBar.handlerBack,
            handlerUser = stateTopBar.handlerUser,
            modifier = modifier,
            userName = stateAuthUser
                ?.name,
            userPhotoUrl = stateAuthUser
                ?.photoUrl
        )
    }
}

@Composable
fun AppTopBar(
    handlerBack: (() -> Unit)?,
    handlerUser: (() -> Unit)?,
    modifier: Modifier,
    userName: String?,
    userPhotoUrl: String?
) {
    val size = 56.dp

    Row(
        modifier
            .padding(
                end = 8.dp,
                start = 8.dp,
                top = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (handlerBack != null) {
            IconButton(
                handlerBack
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    null,
                    Modifier
                        .size(size)
                        .padding(4.dp)
                )
            }
        }

        Spacer(
            Modifier
                .weight(1f)
        )

        if (handlerUser != null) {
            IconButton(
                handlerUser
            ) {
                UserIcon(
                    size = size,
                    userName = userName,
                    userPhotoUrl = userPhotoUrl
                )
            }
        }
    }
}
