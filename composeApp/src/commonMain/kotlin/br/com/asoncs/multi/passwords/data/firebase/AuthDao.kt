package br.com.asoncs.multi.passwords.data.firebase

import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.asoncs.multi.passwords.data.Dao
import br.com.asoncs.multi.passwords.data.firebase.model.AuthUser

interface AuthDao {

    object Impl : Dao(), AuthDao {
        private val userKey = stringPreferencesKey(
            "AUTH_DAO_USER"
        )

        override suspend fun getUser(): AuthUser? = get(userKey)

        override suspend fun setUser(
            user: AuthUser?
        ) = set(userKey, user)
    }

    suspend fun getUser(): AuthUser? {
        TODO("Not yet implemented")
    }

    suspend fun setUser(
        user: AuthUser?
    ) {
        TODO("Not yet implemented")
    }

}
