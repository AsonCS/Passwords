package br.com.asoncs.multi.passwords

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform