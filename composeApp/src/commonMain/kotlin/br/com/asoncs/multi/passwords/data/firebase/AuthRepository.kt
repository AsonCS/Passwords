package br.com.asoncs.multi.passwords.data.firebase

import br.com.asoncs.multi.passwords.auth.AuthException
import br.com.asoncs.multi.passwords.auth.User
import br.com.asoncs.multi.passwords.data.firebase.model.AuthUser
import br.com.asoncs.multi.passwords.extension.getTimeSeconds

interface AuthRepository {

    class Impl(
        private val dao: AuthDao,
        private val remote: AuthRemote
    ) : AuthRepository {

        override suspend fun getUser(): User? {
            var user = dao
                .getUser() //.also { TAG_DATA.log("AuthRepository.getUser: $it") }
                ?: return null

            if (user.isExpired) {
                val response = remote.refreshToken(
                    user.refreshToken
                )

                if (response.error != null) {
                    dao.setUser(null)
                    return null
                }

                user = user.copy(
                    expiresIn = response.expiresIn!!
                        .toLong(),
                    idToken = response.idToken!!,
                    refreshToken = response.refreshToken!!,
                    timestamp = getTimeSeconds()
                )
                dao.setUser(user)
            }

            return user.toUi()
        }

        override suspend fun logout() {
            dao.setUser(null)
        }

        override suspend fun lookup(
            idToken: String?
        ): User {
            val userDao = dao
                .getUser()

            val response = remote.lookup(
                idToken = userDao
                    ?.idToken
                    ?: idToken
                    ?: throw AuthException.InvalidUserException
            ).also { it.throwOnError() }
                .users!![0]

            return if (userDao != null) {
                val user = AuthUser(
                    expiresIn = response.expiresIn
                        ?.toLong()
                        ?: userDao.expiresIn,
                    idToken = response.idToken
                        ?: userDao.idToken,
                    name = response.displayName,
                    email = response.email,
                    photoUrl = response.photoUrl,
                    refreshToken = response.refreshToken
                        ?: userDao.refreshToken,
                    timestamp = getTimeSeconds(),
                    uid = response.localId
                        ?: userDao.uid
                )
                dao.setUser(user)
                user.toUi()
            } else {
                AuthUser(
                    expiresIn = -1,
                    idToken = "",
                    name = response.displayName,
                    email = response.email,
                    photoUrl = response.photoUrl,
                    refreshToken = "",
                    timestamp = -1,
                    uid = response.localId
                        ?: ""
                ).toUi()
            }
        }

        override suspend fun signIn(
            email: String,
            password: String
        ): User {
            val response = remote.signIn(
                email = email,
                password = password
            ).also { it.throwOnError() }
            val user = AuthUser(
                expiresIn = response.expiresIn!!
                    .toLong(),
                idToken = response.idToken!!,
                name = null,
                email = email,
                photoUrl = null,
                refreshToken = response.refreshToken!!,
                timestamp = getTimeSeconds(),
                uid = response.localId!!
            )

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
            ).also { it.throwOnError() }
            val user = AuthUser(
                expiresIn = response.expiresIn!!
                    .toLong(),
                idToken = response.idToken!!,
                name = null,
                email = email,
                photoUrl = null,
                refreshToken = response.refreshToken!!,
                timestamp = getTimeSeconds(),
                uid = response.localId!!
            )

            dao.setUser(user)

            return user.toUi()
        }

        override suspend fun update(
            displayName: String,
            idToken: String?,
            photoUrl: String
        ): User {
            val userDao = dao
                .getUser()

            val response = remote.update(
                displayName = displayName,
                idToken = userDao
                    ?.idToken
                    ?: idToken
                    ?: throw AuthException.InvalidUserException,
                photoUrl = photoUrl
            ).also { it.throwOnError() }

            return if (userDao != null) {
                val user = AuthUser(
                    expiresIn = response.expiresIn
                        ?.toLong()
                        ?: userDao.expiresIn,
                    idToken = response.idToken
                        ?: userDao.idToken,
                    name = response.displayName,
                    email = response.email,
                    photoUrl = response.photoUrl,
                    refreshToken = response.refreshToken
                        ?: userDao.refreshToken,
                    timestamp = getTimeSeconds(),
                    uid = response.localId
                        ?: userDao.uid
                )
                dao.setUser(user)
                user.toUi()
            } else {
                AuthUser(
                    expiresIn = -1,
                    idToken = "",
                    name = response.displayName,
                    email = response.email,
                    photoUrl = response.photoUrl,
                    refreshToken = "",
                    timestamp = -1,
                    uid = response.localId
                        ?: ""
                ).toUi()
            }
        }

    }

    suspend fun getUser(): User? {
        TODO("Not yet implemented")
    }

    suspend fun logout() {
        TODO("Not yet implemented")
    }

    suspend fun lookup(
        idToken: String?
    ): User {
        TODO("Not yet implemented")
    }

    suspend fun signIn(
        email: String,
        password: String
    ): User {
        TODO("Not yet implemented")
    }

    suspend fun signUp(
        email: String,
        password: String
    ): User {
        TODO("Not yet implemented")
    }

    suspend fun update(
        displayName: String,
        idToken: String?,
        photoUrl: String
    ): User

}
