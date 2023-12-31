package no.app.data.repository

import no.app.data.model.api.UserApiModelList
import no.app.data.model.api.UserDetailApiModel
import no.app.data.utils.ApiResult

interface UserRepository {
    suspend fun getAllUsers(perPage: Int, since: Int): ApiResult<UserApiModelList>
    suspend fun getUserByUsername(username: String): ApiResult<UserDetailApiModel>
}
