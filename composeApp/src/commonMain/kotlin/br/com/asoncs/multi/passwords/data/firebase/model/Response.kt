package br.com.asoncs.multi.passwords.data.firebase.model

interface Response {
    val error: Error?

    fun throwOnError() = if (error != null) {
        throw Throwable(error.toString())
    } else this
}
