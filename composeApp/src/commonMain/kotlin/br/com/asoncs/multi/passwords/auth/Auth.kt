package br.com.asoncs.multi.passwords.auth

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default

interface Auth {

    var onEmit: (AuthState) -> Unit

    suspend fun login(
        password: String,
        username: String
    ) {
        TODO("Not yet implemented")
    }

    suspend fun loginWithGoogle() {
        TODO("Not yet implemented")
    }

    suspend fun logout() {
        TODO("Not yet implemented")
    }

    suspend fun signup(
        password: String,
        username: String
    ) {
        TODO("Not yet implemented")
    }

    fun verifyPassword(
        password: String
    ): String {
        return password.trim().let {
            val isValid =
                it == "123456" || Regex(
                    "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$"
                ).matches(it)
            if (isValid)
                it
            else
                throw AuthException.InvalidPasswordException
        }
    }

    fun verifyUsername(
        username: String
    ): String {
        return username.trim().let {
            val isValid = Regex(
                "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
            ).matches(it)
            if (isValid)
                it
            else
                throw AuthException.InvalidUserNameException
        }
    }

}

sealed class AuthState {
    data class LoggedIn(val user: User) : AuthState()
    data object LoggedOut : AuthState()
    data object Unknown : AuthState()
}

object AuthMock : Auth {

    init {
        CoroutineScope(Default).launch {
            delay(3_000)
            onEmit(AuthState.LoggedOut)
        }
    }

    override var onEmit: (AuthState) -> Unit = {}

    override suspend fun login(
        password: String,
        username: String
    ) {
        loginWithGoogle()
    }

    override suspend fun loginWithGoogle() {
        delay(3_000)
        onEmit(
            AuthState.LoggedIn(
                User(
                    "AsonCS",
                    "asoncs_github_mock@mock.com.br",
                    "https://avatars.githubusercontent.com/u/42609750?v=4",
                    "uid"
                )
            )
        )
    }

    override suspend fun logout() {
        onEmit(AuthState.LoggedOut)
    }

    override suspend fun signup(
        password: String,
        username: String
    ) {
        loginWithGoogle()
    }

}
