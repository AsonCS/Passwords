package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
data class ResponseLookup(
    @SerialName("error")
    override val error: Error? = null,
    @SerialName("kind")
    override val kind: String? = null,
    @SerialName("users")
    val users: List<User>? = null
) : Response {
    @Serializable
    data class User(
        @SerialName("createdAt")
        val createdAt: String? = null, // The timestamp, in milliseconds, that the account was created at.
        @SerialName("customAuth")
        val customAuth: Boolean? = null, // Whether the account is authenticated by the developer.
        @SerialName("disabled")
        val disabled: Boolean? = null, // Whether the account is disabled or not.
        @SerialName("displayName")
        val displayName: String? = null, // The display name for the account.
        @SerialName("email")
        val email: String? = null, // The email of the account.
        @SerialName("emailVerified")
        val emailVerified: Boolean? = null, // Whether or not the account's email has been verified.
        @SerialName("expiresIn")
        val expiresIn: String? = null, // The number of seconds in which the ID token expires.
        @SerialName("idToken")
        val idToken: String? = null, // New Firebase Auth ID token for user.
        @SerialName("lastLoginAt")
        val lastLoginAt: String? = null, // The timestamp, in milliseconds, that the account last logged in at.
        @SerialName("localId")
        val localId: String? = null, // The uid of the current user.
        @SerialName("passwordHash")
        val passwordHash: String? = null, // Hash version of password.
        @SerialName("passwordUpdatedAt")
        val passwordUpdatedAt: Double? = null, // The timestamp, in milliseconds, that the account password was last changed.
        @SerialName("validSince")
        val validSince: String? = null, // The timestamp, in seconds, which marks a boundary, before which Firebase ID token are considered revoked.
        @SerialName("photoUrl")
        val photoUrl: String? = null, // The photo Url for the account.
        @SerialName("providerUserInfo")
        val providerUserInfo: JsonArray? = null, // List of all linked provider objects which contain "providerId" and "federatedId".
        @SerialName("refreshToken")
        val refreshToken: String? = null // A Firebase Auth refresh token.
    )
}

/*
{
    "kind": "identitytoolkit#GetAccountInfoResponse",
    "users": [
        {
            "localId": "",
            "email": "abc@com.br",
            "displayName": "Abc",
            "photoUrl": "https://raw.githubusercontent.com/AsonCS/Passwords/refs/heads/dev/emojis/emoji_1.png",
            "passwordHash": "UkVEQUNURUQ=",
            "emailVerified": false,
            "passwordUpdatedAt": 1729113980480,
            "providerUserInfo": [
                {
                    "providerId": "password",
                    "displayName": "Abc",
                    "photoUrl": "https://raw.githubusercontent.com/AsonCS/Passwords/refs/heads/dev/emojis/emoji_1.png",
                    "federatedId": "abc@com.br",
                    "email": "abc@com.br",
                    "rawId": "abc@com.br"
                }
            ],
            "validSince": "1729113980",
            "lastLoginAt": "1729191949749",
            "createdAt": "1729113980480",
            "lastRefreshAt": "2024-10-17T19:05:49.749Z"
        }
    ]
}
 */
