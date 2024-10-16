package br.com.asoncs.multi.passwords.data.test.model

import br.com.asoncs.multi.passwords.core.model.GithubUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUserRemote(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("login")
    val login: String? = null
) {
    fun toUi(
        exampleCounter: Int
    ): GithubUser {
        return GithubUser(
            exampleCounter = exampleCounter,
            id = id
                ?: 0,
            name = name
                ?: "",
            login = login
                ?: ""
        )
    }
}
