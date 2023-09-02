package no.app.data.utils

import no.app.data.db.models.UserDetailEntity
import no.app.data.model.api.UserDetailApiModel

fun UserDetailEntity.toApiModel() = UserDetailApiModel(
    login = login,
    id = id,
    avatarUrl = avatarUrl,
    type = type,
    name = name,
    company = company,
    location = location,
    email = email,
)

fun UserDetailApiModel.toEntity() = UserDetailEntity(
    login = login,
    id = id,
    avatarUrl = avatarUrl,
    type = type,
    name = name,
    company = company,
    location = location,
    email = email,
)
