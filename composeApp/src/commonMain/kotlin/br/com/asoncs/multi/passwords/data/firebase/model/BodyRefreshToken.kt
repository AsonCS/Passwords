package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BodyRefreshToken(
    @SerialName("refresh_token")
    val token: String, // A Firebase Auth refresh token.
    @SerialName("grant_type")
    val grantType: String = "refresh_token" // The refresh token's grant type, always "refresh_token".
) {
    fun toFormUrlEncoded(): String {
        return "grant_type=$grantType&refresh_token=$token"
    }
}
