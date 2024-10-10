package br.com.asoncs.multi.passwords.auth

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface Auth {

    val authState: StateFlow<AuthState>

    suspend fun login(
        username: String,
        password: String
    ) {
        TODO("Not yet implemented")
    }

    suspend fun loginWithGoogle() {
        TODO("Not yet implemented")
    }

    suspend fun logout() {
        TODO("Not yet implemented")
    }

}

sealed class AuthState {
    data class LoggedIn(val user: User) : AuthState()
    data object LoggedOut : AuthState()
    data object Unknown : AuthState()
}

object AuthMock : Auth {

    override val authState = MutableStateFlow<AuthState>(AuthState.Unknown)

    init {
        CoroutineScope(Default).launch {
            delay(3_000)
            authState.emit(AuthState.LoggedOut)
        }
    }

    override suspend fun login(
        username: String,
        password: String
    ) {
        loginWithGoogle()
    }

    override suspend fun loginWithGoogle() {
        delay(3_000)
        authState.emit(
            AuthState.LoggedIn(
                User(
                    "Son",
                    "abc@com.br",
                    null,
                    "uid"
                )
            )
        )
    }

    override suspend fun logout() {
        authState.emit(AuthState.LoggedOut)
    }

}
