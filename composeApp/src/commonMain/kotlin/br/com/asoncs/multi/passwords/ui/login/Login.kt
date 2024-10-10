package br.com.asoncs.multi.passwords.ui.login

import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.ui.navigation.LoginDestination
import kotlinx.coroutines.flow.StateFlow

const val TAG_LOGIN = "login"

data object LoginScreenDestination : LoginDestination("login")

class LoginProps(
    val appName: String,
    val googleLogin: String,
    val image: Painter,
    val login: String,
    val onGoogleLogin: () -> Unit,
    val onLogin: () -> Unit,
    val onSignup: () -> Unit,
    val onUpdatePassword: (String) -> Unit,
    val onUpdateUsername: (String) -> Unit,
    val password: String,
    val passwordPlaceholder: String,
    val signup: String,
    val userName: String,
    val userNamePlaceholder: String,
)

sealed class LoginState(
    val username: String = "",
    val password: String = ""
) {
    class Filling(
        val errorMessage: String? = null,
        username: String = "",
        password: String = ""
    ) : LoginState(username, password)

    class Loading(
        username: String,
        password: String
    ) : LoginState(username, password)
}

abstract class LoginViewModel : ViewModel() {
    open val state: StateFlow<LoginState>
        get() = TODO("Not yet implemented")

    open fun login() {
        TODO("Not yet implemented")
    }

    open fun loginWithGoogle() {
        TODO("Not yet implemented")
    }

    open fun updatePassword(
        password: String
    ) {
        TODO("Not yet implemented")
    }

    open fun updateUsername(
        username: String
    ) {
        TODO("Not yet implemented")
    }
}

fun NavGraphBuilder.loginDestination(
    navigateToSignup: () -> Unit,
) {
    composable(route = LoginScreenDestination.route) {
        LoginScreen(navigateToSignup)
    }
}
