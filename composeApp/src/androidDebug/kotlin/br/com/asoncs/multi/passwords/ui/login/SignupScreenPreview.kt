package br.com.asoncs.multi.passwords.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.asoncs.multi.passwords.R
import br.com.asoncs.multi.passwords.ui.*

@PreviewPixel7
@PreviewPtBr
@Composable
private fun SignupScreenPreview() {
    PreviewContainer {
        SignupScreen(
            modifier = Modifier,
            navigateUp = {},
            props = props(),
            state = LoginState.Filling()
        )
    }
}

@Composable
private fun props() = LoginProps(
    appName = stringResource(R.string.multi_app_name),
    googleLogin = null,
    image = painterResource(R.drawable.compose_multiplatform),
    login = null,
    onGoogleLogin = null,
    onLogin = null,
    onSignup = {},
    onUpdatePassword = {},
    onUpdateUsername = {},
    password = stringResource(R.string.login_screen_password),
    passwordPlaceholder = stringResource(R.string.login_screen_password_placeholder),
    signup = stringResource(R.string.login_screen_signup),
    userName = stringResource(R.string.login_screen_username),
    userNamePlaceholder = stringResource(R.string.login_screen_username_placeholder),
)
