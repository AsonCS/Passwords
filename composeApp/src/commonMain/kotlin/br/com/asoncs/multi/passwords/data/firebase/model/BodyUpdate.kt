package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BodyUpdate(
    @SerialName("displayName")
    val displayName: String, // User's new display name.
    @SerialName("idToken")
    val idToken: String, // A Firebase Auth ID token for the user.
    @SerialName("photoUrl")
    val photoUrl: String, // User's new photo url.
    @SerialName("deleteAttribute")
    val deleteAttribute: List<String>? = null, // List of attributes to delete, "DISPLAY_NAME" or "PHOTO_URL". This will nullify these values.
    @SerialName("returnSecureToken")
    val returnSecureToken: Boolean = true, // Whether or not to return an ID and refresh token.
)
