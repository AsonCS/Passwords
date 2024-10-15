package br.com.asoncs.multi.passwords

import br.com.asoncs.multi.passwords.generated.MultiBuildConfig

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual val isDebug: Boolean = MultiBuildConfig.DEBUG

actual fun getPlatform(): Platform = WasmPlatform()
