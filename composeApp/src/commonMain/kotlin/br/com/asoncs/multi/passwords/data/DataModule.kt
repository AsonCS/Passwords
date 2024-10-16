package br.com.asoncs.multi.passwords.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import br.com.asoncs.multi.passwords.data.firebase.*
import br.com.asoncs.multi.passwords.data.image.ImageRemote
import br.com.asoncs.multi.passwords.data.image.ImageRepository
import br.com.asoncs.multi.passwords.data.test.*
import br.com.asoncs.multi.passwords.extension.log
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.dsl.module

internal interface DataStorePathProducer {
    fun producePath(
        fileName: String
    ): String = fileName
}

internal expect val hostIdentify: String
internal expect val hostToken: String
internal expect val webApiKey: String
internal expect val platformEngine: HttpClientEngineFactory<*>

internal expect fun Module.platformModules()

const val TAG_DATA = "data"

// TODO Check why "single" is not working
private var dataStore: DataStore<Preferences>? = null

fun dataModule() = module {
    platformModules()

    // Api
    single<AuthApi> {
        AuthApi.Impl(
            hostIdentify = hostIdentify,
            hostToken = hostToken,
            webApiKey = webApiKey
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
        dataStore = dataStore ?: PreferenceDataStoreFactory
            .createWithPath(
                produceFile = {
                    get<DataStorePathProducer>()
                        .producePath(
                            fileName = "dice.preferences_pb"
                        ).toPath()
                }
            )
        dataStore!!
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
