package br.com.asoncs.multi.passwords

import br.com.asoncs.multi.passwords.generated.BuildConfig

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual val isDebug: Boolean = BuildConfig.DEBUG

actual fun getPlatform(): Platform = WasmPlatform()
