package br.com.asoncs.multi.passwords.auth

import br.com.asoncs.multi.passwords.data.firebase.AuthRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface Auth {

    val emit: (AuthState) -> Unit
    val repository: AuthRepository

    suspend fun getIdToken(): String? = null

    suspend fun onAuthInit(
        emit: (AuthState) -> Unit
    )

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

    suspend fun lookup(): User {
        val user = repository.lookup(
            getIdToken()
        )
        emit(
            AuthState.LoggedIn(user)
        )

        return user
    }

    suspend fun lookupCatching() {
        runCatching {
            emit(
                AuthState.LoggedIn(
                    repository.lookup(
                        getIdToken()
                    )
                )
            )
        }
    }

    suspend fun signup(
        password: String,
        username: String
    ) {
        TODO("Not yet implemented")
    }

    suspend fun update(
        displayName: String,
        photoUrl: String
    ): User {
        val user = repository.update(
            displayName = displayName,
            idToken = getIdToken(),
            photoUrl = photoUrl
        )
        emit(
            AuthState.LoggedIn(user)
        )

        return user
    }

    fun verifyDisplayName(
        displayName: String?
    ): String {
        if (displayName.isNullOrBlank())
            throw AuthException.InvalidDisplayNameException

        return displayName
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

    fun verifyPhotoUrl(
        photoUrl: String?
    ): String {
        if (photoUrl.isNullOrBlank())
            throw AuthException.InvalidPhotoUrlException

        photoUrl.trim().let {
            val isValid = Regex(
                "(http(s?):)([/|.\\w\\s-])*\\.(?:jpg|gif|png)"
            ).matches(it)
            if (isValid)
                it
            else
                throw AuthException.InvalidPhotoUrlException
        }

        return photoUrl
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

object AuthMock : Auth, KoinComponent {

    init {
        CoroutineScope(Default).launch {
            delay(3_000)
            emit(AuthState.LoggedOut)
        }
    }

    val mockUser = User(
        "AsonCS Mock",
        "asoncs_github_mock@mock.com.br",
        "https://avatars.githubusercontent.com/u/42609750?v=4",
        "uid"
    )

    override var emit: (AuthState) -> Unit = {}
    override val repository by inject<AuthRepository>()

    override suspend fun onAuthInit(
        emit: (AuthState) -> Unit
    ) {
        this.emit = emit
        emit(AuthState.LoggedOut)
    }

    override suspend fun login(
        password: String,
        username: String
    ) {
        loginWithGoogle()
    }

    override suspend fun loginWithGoogle() {
        delay(1_500)
        emit(
            AuthState.LoggedIn(mockUser)
        )
    }

    override suspend fun logout() {
        emit(AuthState.LoggedOut)
    }

    override suspend fun signup(
        password: String,
        username: String
    ) {
        loginWithGoogle()
    }

}
