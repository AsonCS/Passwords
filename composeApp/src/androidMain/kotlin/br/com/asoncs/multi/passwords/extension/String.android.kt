package br.com.asoncs.multi.passwords.extension

import android.util.Log

actual fun platformError(
    message: String,
    tag: String,
    throwable: Throwable?
) {
    Log.e(tag, message, throwable)
}

actual fun platformLog(
    message: String,
    tag: String
) {
    Log.d(tag, message)
}
