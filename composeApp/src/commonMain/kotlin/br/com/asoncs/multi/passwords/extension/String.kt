package br.com.asoncs.multi.passwords.extension

import br.com.asoncs.multi.passwords.isDebug

expect fun platformError(
    message: String,
    tag: String,
    throwable: Throwable? = null
)

expect fun platformLog(
    message: String,
    tag: String
)

fun String.error(
    message: String,
    throwable: Throwable? = null
) {
    if (isDebug) {
        platformError(message, this, throwable)
    }
}

fun String.log(
    message: String
) {
    if (isDebug) {
        platformLog(message, this)
    }
}
