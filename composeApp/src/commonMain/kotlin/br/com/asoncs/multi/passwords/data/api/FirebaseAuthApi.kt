package br.com.asoncs.multi.passwords.data.api

interface FirebaseAuthApi {

    class Impl(
        private val hostIdentify: String,
        private val hostToken: String,
        private val webApiKey: String
    ) : FirebaseAuthApi {
        fun signUp() = "$hostIdentify/"
    }

}
