package br.com.asoncs.multi.passwords

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual val isDebug = true

actual fun getPlatform(): Platform = JVMPlatform()
