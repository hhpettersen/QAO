package no.app.data.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserApiModel(
    val login: String,
    val id: Int,
)

typealias UserApiModelList = List<UserApiModel>
