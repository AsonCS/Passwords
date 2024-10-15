package br.com.asoncs.multi.passwords

import br.com.asoncs.multi.passwords.generated.MultiBuildConfig

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual val isDebug: Boolean = MultiBuildConfig.DEBUG

actual fun getPlatform(): Platform = JVMPlatform()
