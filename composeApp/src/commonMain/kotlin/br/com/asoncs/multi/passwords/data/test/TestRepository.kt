package br.com.asoncs.multi.passwords.data.test

import br.com.asoncs.multi.passwords.core.model.GithubUser
import br.com.asoncs.multi.passwords.data.PlatformDataModule.DataStore

interface TestRepository {

    class Impl(
        dataStore: DataStore,
        private val remote: TestRemote
    ) : TestRepository {

        private val preference = dataStore
            .createPreference(
                Int::class,
                "EXAMPLE_COUNTER"
            )

        override suspend fun githubUser(
            user: String
        ): GithubUser {
            val exampleCounter = preference
                .get()
                ?: -1
            preference.set(exampleCounter + 1)

            return remote.githubUser(user)
                .toUi(exampleCounter)
        }

    }

    suspend fun githubUser(
        user: String
    ): GithubUser

}
