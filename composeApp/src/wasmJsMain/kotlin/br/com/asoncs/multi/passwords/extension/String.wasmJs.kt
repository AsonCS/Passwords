package br.com.asoncs.multi.passwords.extension

import br.com.asoncs.multi.passwords.external.console

actual fun platformError(
    message: String,
    tag: String,
    throwable: Throwable?
) {
    console.error(
        tag,
        message,
        throwable?.message.toString()
    )
}

actual fun platformLog(
    message: String,
    tag: String
) {
    console.log(tag, message)
}
