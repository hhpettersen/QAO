package no.app.data.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserApiModel(
    val login: String,
    val id: Int,
    @Json(name = "avatar_url") val avatarUrl: String
)

typealias UserApiModelList = List<UserApiModel>
