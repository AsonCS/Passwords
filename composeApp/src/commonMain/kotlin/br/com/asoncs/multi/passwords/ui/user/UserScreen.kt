package br.com.asoncs.multi.passwords.ui.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.asoncs.multi.passwords.auth.User
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.component.UserIcon
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserScreen(
    appViewModel: AppViewModel,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = koinViewModel()
) {
    val state by userViewModel.state
        .collectAsState()
    val user by appViewModel.stateAuthUser
        .collectAsState(null)

    user?.let {
        UserScreen(
            modifier = modifier,
            onLogout = userViewModel::logout,
            onUpdateDisplayName = userViewModel::onUpdateDisplayName,
            onUpdatePhotoUrl = userViewModel::onUpdatePhotoUrl,
            user = it,
            state = state
        )
    }

    LaunchedEffect(Unit) {
        appViewModel.stateTopBarUpdate(
            handlerBack = navigateUp
        )
    }
    LaunchedEffect(user == null) {
        user?.let {
            userViewModel.initUserData(it)
        }
    }
}

@Composable
fun UserScreen(
    modifier: Modifier,
    onLogout: () -> Unit,
    onUpdateDisplayName: (String) -> Unit,
    onUpdatePhotoUrl: (String) -> Unit,
    state: UserState,
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
                .align(Alignment.CenterHorizontally),
            size = 144.dp,
            userName = user.name,
            userPhotoUrl = user.photoUrl
        )
        TextField(
            state.photoUrl
                ?: "",
            onUpdatePhotoUrl,
            Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 64.dp
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                capitalization = KeyboardCapitalization.None,
                imeAction = ImeAction.None,
                keyboardType = KeyboardType.Uri
            ),
            label = {
                Text(
                    "Photo URL"
                )
            },
            placeholder = {
                Text(
                    "https://example.com/image.png"
                )
            },
            singleLine = true
        )

        Divider()

        TextField(
            state.displayName
                ?: "",
            onUpdateDisplayName,
            Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.None,
                keyboardType = KeyboardType.Text
            ),
            label = {
                Text(
                    "Display name"
                )
            },
            placeholder = {
                Text(
                    "Your name"
                )
            },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Divider()

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

        if (state.hasChanges) {
            Button(
                onLogout,
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(200.dp)
            ) {
                Text(
                    "Save"
                )
            }
        }
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
