package br.com.asoncs.multi.passwords.data.model

import br.com.asoncs.multi.passwords.core.model.GithubUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUserRemote(
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("login")
    val login: String? = null
) {
    fun toGithubUser(): GithubUser {
        return GithubUser(
            avatarUrl = avatarUrl,
            id = id
                ?: 0,
            name = name
                ?: "",
            login = login
                ?: ""
        )
    }
}
