package br.com.asoncs.multi.passwords.auth

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
