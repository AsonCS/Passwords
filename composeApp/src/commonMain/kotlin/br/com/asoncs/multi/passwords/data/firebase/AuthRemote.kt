package br.com.asoncs.multi.passwords.data.firebase

import br.com.asoncs.multi.passwords.auth.AuthException
import br.com.asoncs.multi.passwords.auth.AuthMock.mockUser
import br.com.asoncs.multi.passwords.data.TAG_DATA
import br.com.asoncs.multi.passwords.data.firebase.model.*
import br.com.asoncs.multi.passwords.extension.error
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
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
        override suspend fun refreshToken(
            token: String
        ): RefreshTokenResponse = client.post {
            url {
                takeFrom(api.refreshToken())
            }
            contentType(FormUrlEncoded)
            setBody(
                RefreshTokenBody(token)
                    .toFormUrlEncoded()
            )
        }.bodyOrError()

        override suspend fun signIn(
            email: String,
            password: String
        ): SignInResponse = client.post {
            url {
                takeFrom(api.signIn())
            }
            contentType(Json)
            setBody(
                SignInBody(
                    email = email,
                    password = password
                )
            )
        }.bodyOrError()

        override suspend fun signUp(
            email: String,
            password: String
        ): SignUpResponse = client.post {
            url {
                takeFrom(api.signUp())
            }
            contentType(Json)
            setBody(
                SignUpBody(
                    email = email,
                    password = password
                )
            )
        }.bodyOrError()

        private suspend inline fun <reified T> HttpResponse.bodyOrError(): T {
            return try {
                body<T>()
            } catch (t: Throwable) {
                val error = body<ResponseError>()
                TAG_DATA.error("AuthRemote.signIn: $error", t)
                throw AuthException.UnknownException(t)
            }
        }

    }

    suspend fun refreshToken(
        token: String
    ): RefreshTokenResponse = RefreshTokenResponse(
        expiresIn = "20",
        idToken = "refreshToken.idToken",
        projectId = "refreshToken.projectId",
        refreshToken = "refreshToken.refreshToken",
        userId = mockUser.uid
    )

    suspend fun signIn(
        email: String,
        password: String
    ): SignInResponse = SignInResponse(
        email = email,
        expiresIn = "20",
        idToken = "signIn.idToken",
        localId = mockUser.uid,
        refreshToken = "signIn.refreshToken",
        registered = true
    )

    suspend fun signUp(
        email: String,
        password: String
    ): SignUpResponse = SignUpResponse(
        email = email,
        expiresIn = "20",
        idToken = "signUp.idToken",
        localId = mockUser.uid,
        refreshToken = "signUp.refreshToken"
    )

}
