package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUp(
    @SerialName("email")
    val email: String? = null, // The email for the newly created user.
    @SerialName("error")
    override val error: Error? = null,
    @SerialName("expiresIn")
    val expiresIn: String? = null, // The number of seconds in which the ID token expires.
    @SerialName("idToken")
    val idToken: String? = null, // A Firebase Auth ID token for the newly created user.
    @SerialName("kind")
    override val kind: String? = null,
    @SerialName("localId")
    val localId: String? = null, // The uid of the newly created user.
    @SerialName("refreshToken")
    val refreshToken: String? = null // A Firebase Auth refresh token for the newly created user.
) : Response

/*
{
    "kind": "identitytoolkit#SignupNewUserResponse",
    "idToken": "",
    "email": "abc@com.br",
    "refreshToken": "",
    "expiresIn": "3600",
    "localId": ""
}
 */
