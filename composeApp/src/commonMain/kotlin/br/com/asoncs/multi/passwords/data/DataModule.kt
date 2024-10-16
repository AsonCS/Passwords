package br.com.asoncs.multi.passwords.data

import br.com.asoncs.multi.passwords.data.api.FirebaseAuthApi
import br.com.asoncs.multi.passwords.data.api.TestApi
import br.com.asoncs.multi.passwords.data.remote.ImageRemote
import br.com.asoncs.multi.passwords.data.remote.TestRemote
import br.com.asoncs.multi.passwords.data.repository.ImageRepository
import br.com.asoncs.multi.passwords.data.repository.TestRepository
import br.com.asoncs.multi.passwords.extension.log
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

expect val hostIdentify: String
expect val platformEngine: HttpClientEngineFactory<*>

const val TAG_DATA = "data"

fun dataModule() = module {
    // Api
    single<FirebaseAuthApi> {
        FirebaseAuthApi.Impl(
            hostIdentify = hostIdentify,
            hostToken = hostToken,
            webApiKey = ""
        )
    }
    single<TestApi> {
        TestApi.Impl(
            baseUrl = "https://api.github.com"
        )
    }

    // Remote
    single<ImageRemote> {
        ImageRemote.Impl(
            client = get()
        )
    }
    single<TestRemote> {
        TestRemote.Impl(
            api = get(),
            client = get()
        )
    }

    // Repository
    single<ImageRepository> {
        ImageRepository.Impl(
            remote = get()
        )
    }
    single<TestRepository> {
        TestRepository.Impl(
            remote = get()
        )
    }

    single {
        HttpClient(platformEngine) {
            install(Logging) {
                //  logger = Logger.DEFAULT
                level = LogLevel.INFO

                logger = object : Logger {
                    override fun log(message: String) {
                        TAG_DATA.log("KtorHttpClient: $message")
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    encodeDefaults = true
                    classDiscriminator = "#class"
                })
            }
            install(HttpCache)
        }
    }
}
