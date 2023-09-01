package no.app.data.api

import no.app.data.model.api.UserApiModel
import no.app.data.model.api.UserApiModelList
import no.app.data.utils.ApiResult

class ApiService(private val apiEndpoints: ApiEndpoints) {

    suspend fun getAllUsers(): ApiResult<UserApiModelList> {
        return try {
            val response = apiEndpoints.getUsers(30, 0)
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

    suspend fun getUserByUsername(username: String): ApiResult<UserApiModel> {
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
