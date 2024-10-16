package br.com.asoncs.multi.passwords.auth

import br.com.asoncs.multi.passwords.auth.AuthState.LoggedIn
import br.com.asoncs.multi.passwords.auth.AuthState.LoggedOut
import br.com.asoncs.multi.passwords.data.firebase.AuthRepository
import br.com.asoncs.multi.passwords.extension.error
import br.com.asoncs.multi.passwords.ui.login.TAG_LOGIN
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object AuthJvm : Auth, KoinComponent {

    private val repository by inject<AuthRepository>()

    private var emit: (AuthState) -> Unit = {}

    override suspend fun onAuthInit(
        emit: (AuthState) -> Unit
    ) {
        this.emit = emit
        runCatching {
            repository.getUser()
        }.onFailure {
            TAG_LOGIN.error("onAuthInit", it)
            emit(LoggedOut)
        }.onSuccess {
            it.emitUser()
        }
    }

    override suspend fun login(
        password: String,
        username: String
    ) {
        repository.signIn(
            email = username,
            password = password
        ).emitUser()
    }

    override suspend fun loginWithGoogle() {
        throw AuthException.NotImplementedYetException
    }

    override suspend fun logout() {
        repository.logout()
        emit(LoggedOut)
    }

    override suspend fun signup(
        password: String,
        username: String
    ) {
        repository.signUp(
            email = username,
            password = password
        ).emitUser()
    }

    private fun User?.emitUser() {
        emit(
            if (this != null)
                LoggedIn(this)
            else
                LoggedOut
        )
    }

}
