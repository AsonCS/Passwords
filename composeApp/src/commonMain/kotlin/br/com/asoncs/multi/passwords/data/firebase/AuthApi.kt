package br.com.asoncs.multi.passwords.data.firebase

interface AuthApi {

    // https://firebase.google.com/docs/reference/rest/auth

    class Impl(
        private val hostIdentify: String,
        private val hostToken: String,
        private val webApiKey: String
    ) : AuthApi {
        override fun lookup() = "$hostIdentify/accounts:lookup?key=$webApiKey"

        override fun refreshToken() = "$hostToken/token?key=$webApiKey"

        override fun signIn() = "$hostIdentify/accounts:signInWithPassword?key=$webApiKey"

        override fun signUp() = "$hostIdentify/accounts:signUp?key=$webApiKey"

        override fun update() = "$hostIdentify/accounts:update?key=$webApiKey"
    }

    fun lookup(): String {
        TODO("Not yet implemented")
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

    fun update(): String {
        TODO("Not yet implemented")
    }

}
