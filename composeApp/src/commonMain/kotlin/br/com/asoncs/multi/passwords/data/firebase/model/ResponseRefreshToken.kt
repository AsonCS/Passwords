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
    "access_token": "eyJhbGciOiJSUzI1NiIsImtpZCI6IjhkOWJlZmQzZWZmY2JiYzgyYzgzYWQwYzk3MmM4ZWE5NzhmNmYxMzciLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vcGFzc3dvcmRzLWFzb24iLCJhdWQiOiJwYXNzd29yZHMtYXNvbiIsImF1dGhfdGltZSI6MTcyOTExMzk4MCwidXNlcl9pZCI6IkFXdGN5NGgwa2NXdnpQVE5UZXFXRjlzY2ZXaDEiLCJzdWIiOiJBV3RjeTRoMGtjV3Z6UFROVGVxV0Y5c2NmV2gxIiwiaWF0IjoxNzI5MTE0MTA5LCJleHAiOjE3MjkxMTc3MDksImVtYWlsIjoiYWJjQGNvbS5iciIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJlbWFpbCI6WyJhYmNAY29tLmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.FxmfcLdONFEX8FT7pOZsMEyRNBRkTt4JNwS-F3hUVwk3eHN03csIo_yYGR5m9dNXjOJ63V2dlhf9zzTebUqsAkn0a68QNM-FiETJ-y3pwQrgMQGLUVCPcA2lPfp5mEutrr0Z5XOpB_LvD8NCq07ZivXE2P1fCNa2kjfVoJP7YddaQQxSoB-wBBJh3PmFxS0C5zoKfmicvnvjp0znxOnIQt2vi7zUKRBxCxcFXrzMpKewRGEn6tfHa0mCrnS-Ex2Tbz_l2Ah5OTgEt1KiWHYkElwL3SVUXU_QbT_3DWKcWbTd-NGyoeGHWBI-EU0PPelEJFT30x_RsFLlEWnTlEdDYQ",
    "expires_in": "3600",
    "token_type": "Bearer",
    "refresh_token": "AMf-vBwgvJtgBuJc4TYwQt2JPM1kmL36XVqx6hfZNrUikOuM1j1WAnjyzx3qz7eLo2HSFRuZFhv4B7i2KXB4F3Tsc-bu0VX8iQdzGtx2XQijG9YQYZWsnEstHGAypp9hshqDp3P_cvidipLAIrCR2Eoy6JNfTcMdlwrWfRu2gaAqSErOVGN3NRiBkFU7adFC8UU0DNCYNC0e",
    "id_token": "eyJhbGciOiJSUzI1NiIsImtpZCI6IjhkOWJlZmQzZWZmY2JiYzgyYzgzYWQwYzk3MmM4ZWE5NzhmNmYxMzciLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vcGFzc3dvcmRzLWFzb24iLCJhdWQiOiJwYXNzd29yZHMtYXNvbiIsImF1dGhfdGltZSI6MTcyOTExMzk4MCwidXNlcl9pZCI6IkFXdGN5NGgwa2NXdnpQVE5UZXFXRjlzY2ZXaDEiLCJzdWIiOiJBV3RjeTRoMGtjV3Z6UFROVGVxV0Y5c2NmV2gxIiwiaWF0IjoxNzI5MTE0MTA5LCJleHAiOjE3MjkxMTc3MDksImVtYWlsIjoiYWJjQGNvbS5iciIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJlbWFpbCI6WyJhYmNAY29tLmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.FxmfcLdONFEX8FT7pOZsMEyRNBRkTt4JNwS-F3hUVwk3eHN03csIo_yYGR5m9dNXjOJ63V2dlhf9zzTebUqsAkn0a68QNM-FiETJ-y3pwQrgMQGLUVCPcA2lPfp5mEutrr0Z5XOpB_LvD8NCq07ZivXE2P1fCNa2kjfVoJP7YddaQQxSoB-wBBJh3PmFxS0C5zoKfmicvnvjp0znxOnIQt2vi7zUKRBxCxcFXrzMpKewRGEn6tfHa0mCrnS-Ex2Tbz_l2Ah5OTgEt1KiWHYkElwL3SVUXU_QbT_3DWKcWbTd-NGyoeGHWBI-EU0PPelEJFT30x_RsFLlEWnTlEdDYQ",
    "user_id": "AWtcy4h0kcWvzPTNTeqWF9scfWh1",
    "project_id": "821675050828"
}
 */
