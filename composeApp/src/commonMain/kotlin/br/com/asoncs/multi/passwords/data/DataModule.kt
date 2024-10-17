package br.com.asoncs.multi.passwords.data

import br.com.asoncs.multi.passwords.data.firebase.*
import br.com.asoncs.multi.passwords.data.image.ImageRemote
import br.com.asoncs.multi.passwords.data.image.ImageRepository
import br.com.asoncs.multi.passwords.data.test.*
import br.com.asoncs.multi.passwords.extension.log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

internal expect val platform: PlatformDataModule

const val TAG_DATA = "data"

fun dataModule() = module {
    // Api
    single<AuthApi> {
        AuthApi.Impl(
            hostIdentify = platform.hostIdentify,
            hostToken = platform.hostToken,
            webApiKey = platform.webApiKey
        )
    }
    single<TestApi> {
        TestApi.Impl(
            baseUrl = "https://api.github.com"
        )
    }

    // Dao
    single<AuthDao> {
        AuthDao.Impl(
            dataStore = get(),
            json = get()
        )
    }

    // Remote
    single<AuthRemote> {
        AuthRemote.Impl(
            api = get(),
            client = get()
        )
        // TODO Remove mock response
        //object : AuthRemote {}
    }
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
    single<AuthRepository> {
        AuthRepository.Impl(
            dao = get(),
            remote = get()
        )
    }
    single<ImageRepository> {
        ImageRepository.Impl(
            remote = get()
        )
    }
    single<TestRepository> {
        TestRepository.Impl(
            dataStore = get(),
            remote = get()
        )
    }

    factory {
        platform.dataStore
    }

    single {
        HttpClient(platform.engine) {
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
