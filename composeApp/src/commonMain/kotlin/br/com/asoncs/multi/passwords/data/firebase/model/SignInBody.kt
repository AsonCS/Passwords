package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInBody(
    @SerialName("email")
    val email: String, // The email the user is signing in with.
    @SerialName("password")
    val password: String, // The password for the account.
    @SerialName("returnSecureToken")
    val returnSecureToken: Boolean = true // Whether or not to return an ID and refresh token. Should always be true.
)
