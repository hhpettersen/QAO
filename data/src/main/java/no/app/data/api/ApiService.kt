package no.app.data.api

import no.app.data.model.api.UserApiModelList
import no.app.data.model.api.UserDetailApiModel
import no.app.data.utils.ApiResult

interface ApiService {
    suspend fun getAllUsers(perPage: Int, since: Int): ApiResult<UserApiModelList>

    suspend fun getUserByUsername(username: String): ApiResult<UserDetailApiModel>
}
