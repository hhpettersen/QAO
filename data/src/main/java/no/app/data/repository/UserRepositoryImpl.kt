package no.app.data.repository

import no.app.data.api.ApiService
import no.app.data.model.api.UserApiModel
import no.app.data.model.api.UserApiModelList
import no.app.data.utils.ApiResult
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiService: ApiService) : UserRepository {

    override suspend fun getAllUsers(): ApiResult<UserApiModelList> {
        return apiService.getAllUsers()
    }

    override suspend fun getUserByUsername(username: String): ApiResult<UserApiModel> {
        return apiService.getUserByUsername(username)
    }
}