package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Error(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("errors")
    val errors: List<ErrorItem>? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: String? = null
) {
    @Serializable
    data class ErrorItem(
        @SerialName("domain")
        val domain: String? = null,
        @SerialName("message")
        val message: String? = null,
        @SerialName("reason")
        val reason: String? = null
    )
}
