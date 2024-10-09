package br.com.asoncs.multi.passwords.ui.login

import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.ui.navigation.LoginDestination
import kotlinx.coroutines.flow.StateFlow

data object LoginScreenDestination : LoginDestination("login")

class LoginProps(
    val appName: String,
    val image: Painter,
    val login: String,
    val password: String,
    val passwordPlaceholder: String,
    val signup: String,
    val userName: String,
    val userNamePlaceholder: String,
)

data class LoginState(
    val username: String = "",
    val password: String = "",
    val updatePassword: (String) -> Unit = {},
    val updateUsername: (String) -> Unit = {}
)

abstract class LoginViewModel : ViewModel() {
    abstract val state: StateFlow<LoginState>

    abstract fun updatePassword(
        password: String
    )

    abstract fun updateUsername(
        username: String
    )
}

fun NavGraphBuilder.loginDestination(
    navigateToHome: () -> Unit,
    navigateToSignup: () -> Unit,
) {
    composable(route = LoginScreenDestination.route) {
        LoginScreen(
            navigateToHome = navigateToHome,
            navigateToSignup = navigateToSignup
        )
    }
}
