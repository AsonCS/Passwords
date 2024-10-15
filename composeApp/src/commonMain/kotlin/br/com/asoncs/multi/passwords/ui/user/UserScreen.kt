package br.com.asoncs.multi.passwords.ui.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.asoncs.multi.passwords.auth.User
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.component.UserIcon
import br.com.asoncs.multi.passwords.ui.home.HomeViewModel

@Composable
fun UserScreen(
    appViewModel: AppViewModel,
    homeViewModel: HomeViewModel,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val user by appViewModel.stateAuthUser
        .collectAsState(null)

    user?.let {
        UserScreen(
            modifier = modifier,
            onLogout = homeViewModel::logout,
            user = it
        )
    }

    LaunchedEffect(Unit) {
        appViewModel.stateTopBarUpdate(
            handlerBack = navigateUp
        )
    }
}

@Composable
fun UserScreen(
    modifier: Modifier,
    onLogout: () -> Unit,
    user: User
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 32.dp,
                vertical = 64.dp
            ),
        verticalArrangement = Arrangement.spacedBy(
            alignment = Alignment.CenterVertically,
            space = 16.dp
        )
    ) {
        UserIcon(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    bottom = 64.dp
                ),
            size = 144.dp,
            userName = user.name,
            userPhotoUrl = user.photoUrl
        )

        Divider()

        user.name?.let { name ->
            Text(
                "Name:"
            )
            Text(
                name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Divider()
        }

        user.email?.let { email ->
            Text(
                "Email:"
            )
            Text(
                email,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Divider()
        }

        Text(
            "UID:"
        )
        Text(
            user.uid,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Divider()

        OutlinedButton(
            onLogout,
            Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
        ) {
            Text(
                "Logout"
            )
        }
    }
}
