package br.com.asoncs.multi.passwords.data.api

interface TestApi {

    class Impl(
        private val baseUrl: String
    ) : TestApi {

        override fun githubUser(
            user: String
        ) = "$baseUrl/users/$user"
    }

    fun githubUser(
        user: String
    ): String

}
