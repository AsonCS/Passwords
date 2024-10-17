package br.com.asoncs.multi.passwords

import br.com.asoncs.multi.passwords.generated.BuildConfig

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual val isDebug: Boolean = BuildConfig.DEBUG

actual fun getPlatform(): Platform = JVMPlatform()
