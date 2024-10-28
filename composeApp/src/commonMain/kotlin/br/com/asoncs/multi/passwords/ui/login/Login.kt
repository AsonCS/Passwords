package br.com.asoncs.multi.passwords.ui.login

import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.login.LoginScreenDestination.Args
import br.com.asoncs.multi.passwords.ui._navigation.LoginDestination
import kotlinx.coroutines.flow.StateFlow

const val TAG_LOGIN = "passwords_app:login"

data object LoginScreenDestination : LoginDestination<Args>(
    "login"
) {
    class Args(
        val appViewModel: AppViewModel,
        val loginViewModel: LoginViewModel,
        val navigateToSignup: () -> Unit
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            LoginScreen(args)
        }
    }
}

internal open class LoginProps(
    val appName: String,
    val googleLogin: String?,
    val image: Painter,
    val login: String?,
    val onGoogleLogin: (() -> Unit)?,
    val onLogin: (() -> Unit)?,
    val onSignup: () -> Unit,
    val onUpdatePassword: (String) -> Unit,
    val onUpdateUsername: (String) -> Unit,
    val password: String,
    val passwordPlaceholder: String,
    val signup: String,
    val userName: String,
    val userNamePlaceholder: String
)

sealed class LoginState {

    abstract val password: String
    abstract val username: String

    data class Filling(
        val errorMessage: String? = null,
        override val password: String = "",
        override val username: String = ""
    ) : LoginState()

    class Loading(
        override val password: String,
        override val username: String
    ) : LoginState()

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

    open fun signup() {
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
