package br.com.asoncs.multi.passwords.data

import io.ktor.client.engine.js.Js

internal actual val platform = object : PlatformDataModule(
    engine = Js
) {}
