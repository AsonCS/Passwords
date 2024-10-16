package br.com.asoncs.multi.passwords.data.firebase.model

import br.com.asoncs.multi.passwords.auth.User
import br.com.asoncs.multi.passwords.extension.getTimeSeconds
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthUser(
    @SerialName("expires_in")
    val expiresIn: Long,
    @SerialName("id_token")
    val idToken: String,
    @SerialName("name")
    val name: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("photoUrl")
    val photoUrl: String? = null,
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("timestamp")
    val timestamp: Long,
    @SerialName("uid")
    val uid: String
) {
    val isExpired: Boolean
        get() = getTimeSeconds() - timestamp > expiresIn

    fun toUi() = User(
        name = name,
        email = email,
        photoUrl = photoUrl,
        uid = uid
    )
}
