package br.com.asoncs.multi.passwords.data.repository

import br.com.asoncs.multi.passwords.core.model.GithubUser
import br.com.asoncs.multi.passwords.data.remote.TestRemote

interface TestRepository {

    class Impl(
        private val remote: TestRemote
    ) : TestRepository {

        override suspend fun githubUser(
            user: String
        ): GithubUser {
            return remote.githubUser(user)
                .toGithubUser()
        }

    }

    suspend fun githubUser(
        user: String
    ): GithubUser

}
