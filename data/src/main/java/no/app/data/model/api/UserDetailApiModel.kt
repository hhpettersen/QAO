package no.app.data.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetailApiModel(
    val login: String,
    val id: Int,
    @Json(name = "avatar_url") val avatarUrl: String,
    val type: UserType,
    val name: String?,
    val company: String?,
    val location: String?,
    val email: String?
)

enum class UserType {
    User,
    Organization;
}
