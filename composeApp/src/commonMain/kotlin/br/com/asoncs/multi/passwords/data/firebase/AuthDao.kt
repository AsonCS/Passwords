package br.com.asoncs.multi.passwords.data.firebase

import br.com.asoncs.multi.passwords.data.PlatformDataModule.DataStore
import br.com.asoncs.multi.passwords.data.firebase.model.AuthUser
import kotlinx.serialization.json.Json

interface AuthDao {

    class Impl(
        dataStore: DataStore,
        json: Json,
    ) : AuthDao {

        private val userPreference = DataStore.PreferenceJson<AuthUser>(
            dataStore,
            json,
            "AUTH_DAO_USER"
        )

        override suspend fun getUser(): AuthUser? = userPreference.get()

        override suspend fun setUser(
            user: AuthUser?
        ) = userPreference.set(user)
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
