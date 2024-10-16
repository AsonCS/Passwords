package br.com.asoncs.multi.passwords.data

import br.com.asoncs.multi.passwords.generated.BuildConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.apache5.Apache5
import org.koin.core.module.Module

internal actual val hostIdentify: String = BuildConfig.FIREBASE_AUTH_API_HOST_IDENTIFY
internal actual val hostToken: String = BuildConfig.FIREBASE_AUTH_API_HOST_TOKEN
internal actual val webApiKey: String = BuildConfig.FIREBASE_WEB_API_KEY
internal actual val platformEngine: HttpClientEngineFactory<*> = Apache5

internal actual fun Module.platformModules() {
    factory<DataStorePathProducer> {
        object : DataStorePathProducer {}
    }
}
