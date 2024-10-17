package br.com.asoncs.multi.passwords.extension

actual fun platformError(
    message: String,
    tag: String,
    throwable: Throwable?
) {
    println("$tag| $message")
    throwable?.printStackTrace()
}

actual fun platformLog(
    message: String,
    tag: String
) {
    println("$tag| $message")
}
