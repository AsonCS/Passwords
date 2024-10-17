package br.com.asoncs.multi.passwords.data.firebase

import br.com.asoncs.multi.passwords.data.TAG_DATA
import br.com.asoncs.multi.passwords.data.firebase.model.*
import br.com.asoncs.multi.passwords.extension.log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.FormUrlEncoded
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.takeFrom

interface AuthRemote {

    // https://firebase.google.com/docs/reference/rest/auth

    class Impl(
        private val api: AuthApi,
        private val client: HttpClient
    ) : AuthRemote {
        override suspend fun lookup(
            idToken: String
        ) = client.post {
            url {
                takeFrom(api.lookup())
            }
            contentType(Json)
            setBody(
                BodyLookup(idToken)
            )
        }.body<ResponseLookup>()
            .also { TAG_DATA.log("AuthRemote.lookup: $it") }

        override suspend fun refreshToken(
            token: String
        ) = client.post {
            url {
                takeFrom(api.refreshToken())
            }
            contentType(FormUrlEncoded)
            setBody(
                BodyRefreshToken(token)
                    .toFormUrlEncoded()
            )
        }.body<ResponseRefreshToken>()
            .also { TAG_DATA.log("AuthRemote.refreshToken: $it") }

        override suspend fun signIn(
            email: String,
            password: String
        ) = client.post {
            url {
                takeFrom(api.signIn())
            }
            contentType(Json)
            setBody(
                BodySignIn(
                    email = email,
                    password = password
                )
            )
        }.body<ResponseSignIn>()
            .also { TAG_DATA.log("AuthRemote.signIn: $it") }

        override suspend fun signUp(
            email: String,
            password: String
        ) = client.post {
            url {
                takeFrom(api.signUp())
            }
            contentType(Json)
            setBody(
                BodySignUp(
                    email = email,
                    password = password
                )
            )
        }.body<ResponseSignUp>()
            .also { TAG_DATA.log("AuthRemote.signUp: $it") }

        override suspend fun update(
            displayName: String,
            idToken: String,
            photoUrl: String
        ) = client.post {
            url {
                takeFrom(api.update())
            }
            contentType(Json)
            setBody(
                BodyUpdate(
                    displayName = displayName,
                    idToken = idToken,
                    photoUrl = photoUrl
                )
            )
        }.body<ResponseUpdate>()
            .also { TAG_DATA.log("AuthRemote.update: $it") }

    }

    suspend fun lookup(
        idToken: String
    ): ResponseLookup {
        TODO("Not yet implemented")
    }

    suspend fun refreshToken(
        token: String
    ): ResponseRefreshToken {
        /*return RefreshTokenResponse(
            expiresIn = "20",
            idToken = "refreshToken.idToken",
            projectId = "refreshToken.projectId",
            refreshToken = "refreshToken.refreshToken",
            userId = mockUser.uid
        ) // */
        TODO("Not yet implemented")
    }

    suspend fun signIn(
        email: String,
        password: String
    ): ResponseSignIn {
        /*return SignInResponse(
            email = email,
            expiresIn = "20",
            idToken = "signIn.idToken",
            localId = mockUser.uid,
            refreshToken = "signIn.refreshToken",
            registered = true
        ) // */
        TODO("Not yet implemented")
    }

    suspend fun signUp(
        email: String,
        password: String
    ): ResponseSignUp {
        /*return SignUpResponse(
            email = email,
            expiresIn = "20",
            idToken = "signUp.idToken",
            localId = mockUser.uid,
            refreshToken = "signUp.refreshToken"
        ) // */
        TODO("Not yet implemented")
    }

    suspend fun update(
        displayName: String,
        idToken: String,
        photoUrl: String
    ): ResponseUpdate {
        TODO("Not yet implemented")
    }

}
