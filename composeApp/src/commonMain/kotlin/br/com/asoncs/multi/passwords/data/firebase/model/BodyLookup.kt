package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BodyLookup(
    @SerialName("idToken")
    val idToken: String // The Firebase ID token of the account.
)
