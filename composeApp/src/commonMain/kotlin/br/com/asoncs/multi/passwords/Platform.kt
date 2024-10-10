package br.com.asoncs.multi.passwords

interface Platform {
    val name: String
}

expect val isDebug: Boolean

expect fun getPlatform(): Platform
