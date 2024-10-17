package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
data class ResponseUpdate(
    @SerialName("displayName")
    val displayName: String? = null, // User's new display name.
    @SerialName("email")
    val email: String? = null, // User's email address.
    @SerialName("error")
    override val error: Error? = null,
    @SerialName("expiresIn")
    val expiresIn: String? = null, // The number of seconds in which the ID token expires.
    @SerialName("idToken")
    val idToken: String? = null, // New Firebase Auth ID token for user.
    @SerialName("localId")
    val localId: String? = null, // The uid of the current user.
    @SerialName("passwordHash")
    val passwordHash: String? = null, // Hash version of password.
    @SerialName("photoUrl")
    val photoUrl: String? = null, // User's new photo url.
    @SerialName("providerUserInfo")
    val providerUserInfo: JsonArray? = null, // List of all linked provider objects which contain "providerId" and "federatedId".
    @SerialName("refreshToken")
    val refreshToken: String? = null // A Firebase Auth refresh token.
) : Response
