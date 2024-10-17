package br.com.asoncs.multi.passwords.extension

import io.ktor.util.date.getTimeMillis

fun Any.error(
    message: String,
    throwable: Throwable? = null,
    tag: String = this::class.simpleName ?: "Any"
) {
    tag.error(message, throwable)
}

fun Any.log(
    message: String,
    tag: String = this::class.simpleName ?: "Any"
) {
    tag.log(message)
}

fun getTimeSeconds() = getTimeMillis() / 1_000
