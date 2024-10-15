package br.com.asoncs.multi.passwords.data

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.apache5.Apache5

actual val platformEngine: HttpClientEngineFactory<*> = Apache5
