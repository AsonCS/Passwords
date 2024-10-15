package br.com.asoncs.multi.passwords.data

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.js.Js

actual val platformEngine: HttpClientEngineFactory<*> = Js
