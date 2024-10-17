package br.com.asoncs.multi.passwords.data.firebase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUp(
    @SerialName("email")
    val email: String? = null, // The email for the newly created user.
    @SerialName("error")
    override val error: Error? = null,
    @SerialName("expiresIn")
    val expiresIn: String? = null, // The number of seconds in which the ID token expires.
    @SerialName("idToken")
    val idToken: String? = null, // A Firebase Auth ID token for the newly created user.
    @SerialName("localId")
    val localId: String? = null, // The uid of the newly created user.
    @SerialName("refreshToken")
    val refreshToken: String? = null // A Firebase Auth refresh token for the newly created user.
) : Response

/*
{
    "kind": "identitytoolkit#SignupNewUserResponse",
    "idToken": "eyJhbGciOiJSUzI1NiIsImtpZCI6IjhkOWJlZmQzZWZmY2JiYzgyYzgzYWQwYzk3MmM4ZWE5NzhmNmYxMzciLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vcGFzc3dvcmRzLWFzb24iLCJhdWQiOiJwYXNzd29yZHMtYXNvbiIsImF1dGhfdGltZSI6MTcyOTExMzk4MCwidXNlcl9pZCI6IkFXdGN5NGgwa2NXdnpQVE5UZXFXRjlzY2ZXaDEiLCJzdWIiOiJBV3RjeTRoMGtjV3Z6UFROVGVxV0Y5c2NmV2gxIiwiaWF0IjoxNzI5MTEzOTgwLCJleHAiOjE3MjkxMTc1ODAsImVtYWlsIjoiYWJjQGNvbS5iciIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJlbWFpbCI6WyJhYmNAY29tLmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.gQs5gp0EyzwsKk57a54HbRD-pwSWnr0x819na_E8gKO1cZnmDfNsimTkz9HWc67MTI0bf9WCZyQSkqmihwximdIwhp4t1H1wROE33xWTUEcVqZ8fFhEgYaF_pBoaRZx5xTsNvTjGdfaCO05Sgl80_MRYy89DeVSnXUQC0wMxrxSEhGS9iCXyKXZ6entw9yI4gt3EpVDiMkRcQSoUpdi3l_EqAaJT7upkXIWVsUjy-vqs4FNvw4hd8dflVcJhYcOw7ZVOd26nKgSa0zcejF5VcZTrSfR6HVvUsWfHuljdVc3PQf3KOZrywTTrTOho5mBts49lqeHSPSMCYoKpZFDyrw",
    "email": "abc@com.br",
    "refreshToken": "AMf-vBwgvJtgBuJc4TYwQt2JPM1kmL36XVqx6hfZNrUikOuM1j1WAnjyzx3qz7eLo2HSFRuZFhv4B7i2KXB4F3Tsc-bu0VX8iQdzGtx2XQijG9YQYZWsnEstHGAypp9hshqDp3P_cvidipLAIrCR2Eoy6JNfTcMdlwrWfRu2gaAqSErOVGN3NRiBkFU7adFC8UU0DNCYNC0e",
    "expiresIn": "3600",
    "localId": "AWtcy4h0kcWvzPTNTeqWF9scfWh1"
}
 */
