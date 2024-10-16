package br.com.asoncs.multi.passwords.data

import br.com.asoncs.multi.passwords.generated.BuildConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.apache5.Apache5

actual val hostIdentify: String = BuildConfig.FIREBASE_AUTH_API_HOST_IDENTIFY
actual val hostToken: String = BuildConfig.FIREBASE_AUTH_API_HOST_TOKEN
actual val webApiKey: String = BuildConfig.FIREBASE_WEB_API_KEY
actual val platformEngine: HttpClientEngineFactory<*> = Apache5
