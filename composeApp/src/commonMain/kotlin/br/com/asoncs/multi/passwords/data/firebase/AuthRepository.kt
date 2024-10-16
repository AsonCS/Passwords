package br.com.asoncs.multi.passwords.data.firebase

import br.com.asoncs.multi.passwords.auth.User
import br.com.asoncs.multi.passwords.data.TAG_DATA
import br.com.asoncs.multi.passwords.data.firebase.model.AuthUser
import br.com.asoncs.multi.passwords.extension.getTimeSeconds
import br.com.asoncs.multi.passwords.extension.log

interface AuthRepository {

    class Impl(
        private val dao: AuthDao,
        private val remote: AuthRemote
    ) : AuthRepository {
        override suspend fun getUser(): User? {
            var user = dao
                .getUser()
                .also { TAG_DATA.log("AuthRepository.getUser: $it") }
                ?: return null

            if (user.isExpired) {
                TAG_DATA.log("AuthRepository.getUser.isExpired")

                val response = remote.refreshToken(
                    user.refreshToken
                )
                TAG_DATA.log("AuthRepository.refreshToken: $response")

                TAG_DATA.log("AuthRepository.getUser.isExpired: $user")
                user = user.copy(
                    expiresIn = response.expiresIn
                        .toLong(),
                    idToken = response.idToken,
                    refreshToken = response.refreshToken,
                    timestamp = getTimeSeconds()
                )
                dao.setUser(user)
            }

            return user.toUi()
        }

        override suspend fun logout() {
            dao.setUser(null)
        }

        override suspend fun signIn(
            email: String,
            password: String
        ): User {
            val response = remote.signIn(
                email = email,
                password = password
            )
            val user = AuthUser(
                expiresIn = response.expiresIn
                    .toLong(),
                idToken = response.idToken,
                name = null,
                email = email,
                photoUrl = null,
                refreshToken = response.refreshToken,
                timestamp = getTimeSeconds(),
                uid = response.localId
            )
            TAG_DATA.log("AuthRepository.signIn: $response | $user")

            dao.setUser(user)

            return user.toUi()
        }

        override suspend fun signUp(
            email: String,
            password: String
        ): User {
            val response = remote.signUp(
                email = email,
                password = password
            )
            val user = AuthUser(
                expiresIn = response.expiresIn
                    .toLong(),
                idToken = response.idToken,
                name = null,
                email = email,
                photoUrl = null,
                refreshToken = response.refreshToken,
                timestamp = getTimeSeconds(),
                uid = response.localId
            )
            TAG_DATA.log("AuthRepository.signUp: $response | $user")

            dao.setUser(user)

            return user.toUi()
        }
    }

    suspend fun getUser(): User?

    suspend fun logout()

    suspend fun signIn(
        email: String,
        password: String
    ): User

    suspend fun signUp(
        email: String,
        password: String
    ): User

}
