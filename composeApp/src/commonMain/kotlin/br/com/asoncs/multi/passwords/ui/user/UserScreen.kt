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
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.component.Loading
import br.com.asoncs.multi.passwords.ui.component.UserIcon
import br.com.asoncs.multi.passwords.ui.user.UserState.Filling
import br.com.asoncs.multi.passwords.ui.user.UserState.Loading
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

    UserScreen(
        modifier = modifier,
        onLogout = userViewModel::logout,
        onReload = userViewModel::reload,
        onSave = userViewModel::save,
        onUpdateDisplayName = userViewModel::updateDisplayName,
        onUpdatePhotoUrl = userViewModel::updatePhotoUrl,
        state = state
    )

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
    onReload: () -> Unit,
    onSave: () -> Unit,
    onUpdateDisplayName: (String) -> Unit,
    onUpdatePhotoUrl: (String) -> Unit,
    state: UserState
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
        if (state is Filling && state.errorMessage != null) {
            Text(
                state.errorMessage,
                color = MaterialTheme.colors.error
            )
        }
        if (state is Loading) {
            Loading(
                Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }

        UserIcon(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            size = 144.dp,
            userName = state.user
                ?.name,
            userPhotoUrl = state.user
                ?.photoUrl
        )
        TextField(
            state.user
                ?.photoUrl
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
            state.user
                ?.name
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

        state.user
            ?.email
            ?.let { email ->
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

        state.user
            ?.uid
            ?.let { uid ->
                Text(
                    "UID:"
                )
                Text(
                    uid,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Divider()
            }

        if (state.hasChanges) {
            Button(
                onSave,
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

        OutlinedButton(
            onReload,
            Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
        ) {
            Text(
                "Reload"
            )
        }
    }
}
