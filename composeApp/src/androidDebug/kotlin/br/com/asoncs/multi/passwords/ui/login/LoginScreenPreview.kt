package br.com.asoncs.multi.passwords.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncs.multi.passwords.R
import br.com.asoncs.multi.passwords.ui.*

@PreviewPixel7
@Composable
private fun LoginScreenPreview(
    @PreviewParameter(ValuesProvider::class) state: LoginState
) {
    PreviewContainer {
        LoginScreen(
            modifier = Modifier,
            props = props(),
            state = state
        )
    }
}

@PreviewPtBr
@Composable
private fun LoginScreenPreview() {
    PreviewContainer {
        LoginScreen(
            modifier = Modifier,
            props = props(),
            state = LoginState.Filling()
        )
    }
}

private class ValuesProvider : PreviewParameterProvider<LoginState> {
    override val values = sequenceOf(
        LoginState.Filling(),
        LoginState.Filling(
            username = "username@com.br",
            password = "password"
        )
    )
}

@Composable
private fun props() = LoginProps(
    appName = stringResource(R.string.multi_app_name),
    googleLogin = stringResource(R.string.login_screen_google_login),
    image = painterResource(R.drawable.compose_multiplatform),
    login = stringResource(R.string.login_screen_login),
    onGoogleLogin = {},
    onLogin = {},
    onSignup = {},
    onUpdatePassword = {},
    onUpdateUsername = {},
    password = stringResource(R.string.login_screen_password),
    passwordPlaceholder = stringResource(R.string.login_screen_password_placeholder),
    signup = stringResource(R.string.login_screen_signup),
    userName = stringResource(R.string.login_screen_username),
    userNamePlaceholder = stringResource(R.string.login_screen_username_placeholder),
)
