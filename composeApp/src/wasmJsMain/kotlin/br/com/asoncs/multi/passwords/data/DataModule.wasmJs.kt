package br.com.asoncs.multi.passwords.data

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.js.Js
import org.koin.core.module.Module

internal actual val hostIdentify: String = ""
internal actual val hostToken: String = ""
internal actual val webApiKey: String = ""
internal actual val platformEngine: HttpClientEngineFactory<*> = Js

internal actual fun Module.platformModules() {
    factory<DataStorePathProducer> {
        object : DataStorePathProducer {}
    }
}
