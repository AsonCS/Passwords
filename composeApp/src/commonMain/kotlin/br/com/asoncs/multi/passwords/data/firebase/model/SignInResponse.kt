package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    @SerialName("email")
    val email: String, // The email for the authenticated user.
    @SerialName("expiresIn")
    val expiresIn: String, // The number of seconds in which the ID token expires.
    @SerialName("idToken")
    val idToken: String, // A Firebase Auth ID token for the authenticated user.
    @SerialName("localId")
    val localId: String, // The uid of the authenticated user.
    @SerialName("refreshToken")
    val refreshToken: String, // A Firebase Auth refresh token for the authenticated user.
    @SerialName("registered")
    val registered: Boolean // Whether the email is for an existing account.
)

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
