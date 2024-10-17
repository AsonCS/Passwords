package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseRefreshToken(
    @SerialName("error")
    override val error: Error? = null,
    @SerialName("expires_in")
    val expiresIn: String? = null, // The number of seconds in which the ID token expires.
    @SerialName("id_token")
    val idToken: String? = null, // A Firebase Auth ID token.
    @SerialName("project_id")
    val projectId: String? = null, // Your Firebase project ID.
    @SerialName("refresh_token")
    val refreshToken: String? = null, // The Firebase Auth refresh token provided in the request or a new refresh token.
    @SerialName("token_type")
    val tokenType: String? = null, // The type of the refresh token, always "Bearer".
    @SerialName("user_id")
    val userId: String? = null // The uid corresponding to the provided ID token.
) : Response

/*
{
    "error": {
        "code": 400,
        "message": "INVALID_REFRESH_TOKEN",
        "status": "INVALID_ARGUMENT"
    }
}

{
    "access_token": "",
    "expires_in": "3600",
    "token_type": "Bearer",
    "refresh_token": "",
    "id_token": "",
    "user_id": "",
    "project_id": "821675050828"
}
 */
