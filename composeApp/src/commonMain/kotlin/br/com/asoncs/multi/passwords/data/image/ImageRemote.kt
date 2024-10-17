package br.com.asoncs.multi.passwords.data.image

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsBytes

interface ImageRemote {

    class Impl(
        private val client: HttpClient
    ) : ImageRemote {
        override suspend fun getImage(
            url: String?
        ): ByteArray? {
            if (url == null) return null

            return client.get(url)
                .bodyAsBytes()
        }
    }

    suspend fun getImage(
        url: String?
    ): ByteArray?

}
