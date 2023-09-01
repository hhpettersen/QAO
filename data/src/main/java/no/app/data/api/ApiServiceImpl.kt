package no.app.data.api

import no.app.data.model.api.UserApiModelList
import no.app.data.model.api.UserDetailApiModel
import no.app.data.utils.ApiResult

class ApiServiceImpl(private val apiEndpoints: ApiEndpoints) : ApiService {

    override suspend fun getAllUsers(perPage: Int, since: Int): ApiResult<UserApiModelList> {
        return try {
            val response = apiEndpoints.getUsers(perPage, since)
            if (response.isSuccessful) {
                response.body()?.let {
                    ApiResult.Success(it)
                } ?: ApiResult.Error(Exception("Response body was null"))
            } else {
                ApiResult.Error(Exception("Failed to get users, HTTP code: ${response.code()}"))
            }
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    override suspend fun getUserByUsername(username: String): ApiResult<UserDetailApiModel> {
        return try {
            val response = apiEndpoints.getUserByUsername(username)
            if (response.isSuccessful) {
                response.body()?.let {
                    ApiResult.Success(it)
                } ?: ApiResult.Error(Exception("Response body was null"))
            } else {
                ApiResult.Error(Exception("Failed to get user, HTTP code: ${response.code()}"))
            }
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}
