package br.com.asoncs.multi.passwords.data.remote

import br.com.asoncs.multi.passwords.data.api.TestApi
import br.com.asoncs.multi.passwords.data.model.GithubUserRemote
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.takeFrom

interface TestRemote {

    class Impl(
        private val api: TestApi,
        private val client: HttpClient
    ) : TestRemote {

        override suspend fun githubUser(
            user: String
        ): GithubUserRemote {
            return client.get {
                url {
                    takeFrom(
                        api.githubUser(user)
                    )
                }
            }.body()
        }

    }

    suspend fun githubUser(
        user: String
    ): GithubUserRemote

}
