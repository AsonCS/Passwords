package br.com.asoncs.multi.passwords.data.test

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import br.com.asoncs.multi.passwords.core.model.GithubUser

val EXAMPLE_COUNTER = intPreferencesKey("example_counter")

interface TestRepository {

    class Impl(
        private val dataStore: DataStore<Preferences>,
        private val remote: TestRemote
    ) : TestRepository {

        override suspend fun githubUser(
            user: String
        ): GithubUser {
            var exampleCounter = -1
            dataStore.edit { settings ->
                exampleCounter = settings[EXAMPLE_COUNTER] ?: 0
                settings[EXAMPLE_COUNTER] = exampleCounter + 1
            }
            return remote.githubUser(user)
                .toUi(exampleCounter)
        }

    }

    suspend fun githubUser(
        user: String
    ): GithubUser

}
