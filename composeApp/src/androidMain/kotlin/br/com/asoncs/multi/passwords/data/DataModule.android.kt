package br.com.asoncs.multi.passwords.data

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual val platformEngine: HttpClientEngineFactory<*> = OkHttp
