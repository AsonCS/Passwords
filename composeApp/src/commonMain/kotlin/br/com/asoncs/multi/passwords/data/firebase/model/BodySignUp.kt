package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BodySignUp(
    @SerialName("email")
    val email: String, // The email for the user to create.
    @SerialName("password")
    val password: String, // The password for the user to create.
    @SerialName("returnSecureToken")
    val returnSecureToken: Boolean = true // Whether or not to return an ID and refresh token. Should always be true.
)
