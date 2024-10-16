package br.com.asoncs.multi.passwords.data

import android.content.Context
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module

internal actual val hostIdentify: String = ""
internal actual val hostToken: String = ""
internal actual val webApiKey: String = ""
internal actual val platformEngine: HttpClientEngineFactory<*> = OkHttp

internal actual fun Module.platformModules() {
    factory<DataStorePathProducer> {
        object : DataStorePathProducer {
            override fun producePath(
                fileName: String
            ): String = get<Context>()
                .filesDir
                .resolve(fileName)
                .absolutePath
        }
    }
}
