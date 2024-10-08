package br.com.asoncs.multi.passwords.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncs.multi.passwords.R
import br.com.asoncs.multi.passwords.ui.PreviewPixel7
import br.com.asoncs.multi.passwords.ui.PreviewPtBr

@PreviewPixel7
@Composable
private fun LoginScreenPreview(
    @PreviewParameter(ValuesProvider::class) state: LoginState
) {
    LoginScreen(
        Modifier,
        {},
        {},
        props(),
        state
    )
}

@PreviewPtBr
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        Modifier,
        {},
        {},
        props(),
        LoginState()
    )
}

class ValuesProvider : PreviewParameterProvider<LoginState> {
    override val values = sequenceOf(
        LoginState(),
        LoginState(
            username = "username@com.br",
            password = "password"
        )
    )
}

@Composable
private fun props() = LoginProps(
    appName = stringResource(R.string.multi_app_name),
    image = painterResource(R.drawable.compose_multiplatform),
    login = stringResource(R.string.login_screen_login),
    password = stringResource(R.string.login_screen_password),
    passwordPlaceholder = stringResource(R.string.login_screen_password_placeholder),
    signup = stringResource(R.string.login_screen_signup),
    userName = stringResource(R.string.login_screen_username),
    userNamePlaceholder = stringResource(R.string.login_screen_username_placeholder),
)
