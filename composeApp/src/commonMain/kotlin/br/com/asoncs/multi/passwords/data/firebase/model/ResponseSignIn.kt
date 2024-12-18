package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignIn(
    @SerialName("email")
    val email: String? = null, // The email for the authenticated user.
    @SerialName("error")
    override val error: Error? = null,
    @SerialName("expiresIn")
    val expiresIn: String? = null, // The number of seconds in which the ID token expires.
    @SerialName("idToken")
    val idToken: String? = null, // A Firebase Auth ID token for the authenticated user.
    @SerialName("kind")
    override val kind: String? = null,
    @SerialName("localId")
    val localId: String? = null, // The uid of the authenticated user.
    @SerialName("refreshToken")
    val refreshToken: String? = null, // A Firebase Auth refresh token for the authenticated user.
    @SerialName("registered")
    val registered: Boolean? = null // Whether the email is for an existing account.
) : Response

/*
{
    "error": {
        "code": 400,
        "message": "INVALID_LOGIN_CREDENTIALS",
        "errors": [
            {
                "message": "INVALID_LOGIN_CREDENTIALS",
                "domain": "global",
                "reason": "invalid"
            }
        ]
    }
}
 */
