package br.com.asoncs.multi.passwords.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.component.Loading
import br.com.asoncs.multi.passwords.ui.login.LoginState.Filling
import br.com.asoncs.multi.passwords.ui.login.LoginState.Loading
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import passwords.composeapp.generated.resources.*

@Composable
fun SignupScreen(
    appViewModel: AppViewModel,
    loginViewModel: LoginViewModel,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by loginViewModel.state
        .collectAsState()

    SignupScreen(
        modifier = modifier,
        props = LoginProps(
            appName = stringResource(Res.string.multi_app_name),
            googleLogin = null,
            image = painterResource(Res.drawable.compose_multiplatform),
            login = null,
            onGoogleLogin = null,
            onLogin = null,
            onSignup = loginViewModel::signup,
            onUpdatePassword = loginViewModel::updatePassword,
            onUpdateUsername = loginViewModel::updateUsername,
            password = stringResource(Res.string.login_screen_password),
            passwordPlaceholder = stringResource(Res.string.login_screen_password_placeholder),
            signup = stringResource(Res.string.login_screen_signup),
            userName = stringResource(Res.string.login_screen_username),
            userNamePlaceholder = stringResource(Res.string.login_screen_username_placeholder)
        ),
        state = state
    )

    LaunchedEffect(Unit) {
        appViewModel.stateTopBarUpdate(
            handlerBack = navigateUp
        )
    }
}

@Composable
internal fun SignupScreen(
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
