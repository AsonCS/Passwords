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
import br.com.asoncs.multi.passwords.ui.component.Loading
import br.com.asoncs.multi.passwords.ui.login.LoginState.Filling
import br.com.asoncs.multi.passwords.ui.login.LoginState.Loading
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import passwords.composeapp.generated.resources.*

@Composable
fun LoginScreen(
    navigateToSignup: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LoginScreen(
        modifier = modifier,
        props = LoginProps(
            appName = stringResource(Res.string.multi_app_name),
            googleLogin = stringResource(Res.string.login_screen_google_login),
            image = painterResource(Res.drawable.compose_multiplatform),
            login = stringResource(Res.string.login_screen_login),
            onGoogleLogin = viewModel::loginWithGoogle,
            onLogin = viewModel::login,
            onSignup = navigateToSignup,
            onUpdatePassword = viewModel::updatePassword,
            onUpdateUsername = viewModel::updateUsername,
            password = stringResource(Res.string.login_screen_password),
            passwordPlaceholder = stringResource(Res.string.login_screen_password_placeholder),
            signup = stringResource(Res.string.login_screen_signup),
            userName = stringResource(Res.string.login_screen_username),
            userNamePlaceholder = stringResource(Res.string.login_screen_username_placeholder)
        ),
        state = state
    )
}

@Composable
internal fun LoginScreen(
    modifier: Modifier,
    props: LoginProps,
    state: LoginState
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                alignment = Alignment.CenterVertically,
                space = 16.dp
            )
    ) {
        Logo(
            Modifier,
            props
        )

        when (state) {
            is Filling -> {
                if (state.errorMessage != null) {
                    Text(
                        state.errorMessage,
                        color = MaterialTheme.colors.error
                    )
                }

                Fields(
                    Modifier,
                    props,
                    state
                )

                Buttons(
                    Modifier,
                    props
                )
            }

            is Loading -> Loading()
        }
    }
}

@Composable
internal fun Logo(
    modifier: Modifier,
    props: LoginProps
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
    }
}

@Composable
internal fun Fields(
    modifier: Modifier,
    props: LoginProps,
    state: Filling
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                alignment = Alignment.CenterVertically,
                space = 8.dp
            )
    ) {
        TextField(
            state.username,
            props.onUpdateUsername,
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

        TextField(
            state.password,
            props.onUpdatePassword,
            keyboardActions = KeyboardActions(
                onDone = {
                    props.onLogin
                        ?.invoke()
                        ?: props.onSignup()
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
    }
}

@Composable
internal fun Buttons(
    modifier: Modifier,
    props: LoginProps
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                alignment = Alignment.CenterVertically,
                space = 8.dp
            )
    ) {
        if (props.onLogin != null && props.login != null) {
            Button(
                props.onLogin,
                Modifier
                    .width(200.dp)
            ) {
                Text(
                    props.login
                )
            }
        }
        OutlinedButton(
            props.onSignup,
            Modifier
                .width(200.dp)
        ) {
            Text(
                props.signup
            )
        }
        if (props.onGoogleLogin != null && props.googleLogin != null) {
            Button(
                props.onGoogleLogin,
                Modifier
                    .width(200.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                )
            ) {
                Text(
                    props.googleLogin
                )
            }
        }
    }
}
