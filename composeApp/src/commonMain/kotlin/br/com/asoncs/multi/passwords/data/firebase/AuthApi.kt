package br.com.asoncs.multi.passwords.data.firebase

interface AuthApi {

    // https://firebase.google.com/docs/reference/rest/auth

    class Impl(
        private val hostIdentify: String,
        private val hostToken: String,
        private val webApiKey: String
    ) : AuthApi {
        override fun refreshToken() = "$hostToken/token?key=$webApiKey"

        override fun signIn() = "$hostIdentify/accounts:signInWithPassword?key=$webApiKey"

        override fun signUp() = "$hostIdentify/accounts:signUp?key=$webApiKey"
    }

    fun refreshToken(): String {
        TODO("Not yet implemented")
    }

    fun signIn(): String {
        TODO("Not yet implemented")
    }

    fun signUp(): String {
        TODO("Not yet implemented")
    }

}
