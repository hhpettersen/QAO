package no.app.data.api

import no.app.data.model.api.UserApiModel
import no.app.data.model.api.UserApiModelList
import no.app.data.utils.ApiResult

interface ApiService {
    suspend fun getAllUsers(): ApiResult<UserApiModelList>

    suspend fun getUserByUsername(username: String): ApiResult<UserApiModel>
}
