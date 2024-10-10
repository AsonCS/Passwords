package br.com.asoncs.multi.passwords

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual val isDebug = true

actual fun getPlatform(): Platform = WasmPlatform()
