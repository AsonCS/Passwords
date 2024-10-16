package br.com.asoncs.multi.passwords.data.firebase

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import br.com.asoncs.multi.passwords.data.firebase.model.AuthUser
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface AuthDao {

    class Impl(
        private val dataStore: DataStore<Preferences>,
        private val json: Json
    ) : AuthDao {

        private val userKey = stringPreferencesKey(
            "AUTH_DAO_USER"
        )

        override suspend fun getUser(): AuthUser? {
            val user = dataStore
                .data
                .first()[userKey]
                ?: return null

            return json.decodeFromString(user)
        }

        override suspend fun setUser(
            user: AuthUser?
        ) {
            dataStore.edit { settings ->
                if (user == null) {
                    settings.remove(userKey)
                } else {
                    settings[userKey] = json.encodeToString(user)
                }
            }
        }

    }

    suspend fun getUser(): AuthUser?

    suspend fun setUser(
        user: AuthUser?
    )

}
