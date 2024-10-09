package br.com.asoncs.multi.passwords.ui.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import passwords.composeapp.generated.resources.*

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    navigateToSignup: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinInject()
) {
    val state by viewModel.state.collectAsState()

    LoginScreen(
        modifier = modifier,
        onLogin = navigateToHome,
        onSignup = navigateToSignup,
        props = LoginProps(
            appName = stringResource(Res.string.multi_app_name),
            image = painterResource(Res.drawable.compose_multiplatform),
            login = stringResource(Res.string.login_screen_login),
            password = stringResource(Res.string.login_screen_password),
            passwordPlaceholder = stringResource(Res.string.login_screen_password_placeholder),
            signup = stringResource(Res.string.login_screen_signup),
            userName = stringResource(Res.string.login_screen_username),
            userNamePlaceholder = stringResource(Res.string.login_screen_username_placeholder),
        ),
        state = state
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier,
    onLogin: () -> Unit,
    onSignup: () -> Unit,
    props: LoginProps,
    state: LoginState
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            props.image,
            null,
            Modifier
                .size(100.dp)
        )
        Text(
            props.appName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            Modifier
                .height(16.dp)
        )
        TextField(
            state.username,
            state.updateUsername,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                capitalization = KeyboardCapitalization.None,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            label = {
                Text(
                    props.userName
                )
            },
            placeholder = {
                Text(
                    props.userNamePlaceholder
                )
            },
            singleLine = true
        )
        Spacer(
            Modifier
                .height(8.dp)
        )
        TextField(
            state.password,
            state.updatePassword,
            keyboardActions = KeyboardActions(
                onDone = {
                    onLogin()
                }
            ),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                capitalization = KeyboardCapitalization.None,
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            label = {
                Text(
                    props.password
                )
            },
            placeholder = {
                Text(
                    props.passwordPlaceholder
                )
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(
            Modifier
                .height(16.dp)
        )
        Button(
            onLogin,
            Modifier
                .width(200.dp)
        ) {
            Text(
                props.login
            )
        }
        Spacer(
            Modifier
                .height(8.dp)
        )
        OutlinedButton(
            onSignup,
            Modifier
                .width(200.dp)
        ) {
            Text(
                props.signup
            )
        }
    }
}
