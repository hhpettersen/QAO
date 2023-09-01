package no.app.data.api

import no.app.data.model.api.UserApiModel
import no.app.data.model.api.UserApiModelList

class ApiService(private val apiEndpoints: ApiEndpoints) {

    suspend fun getAllUsers(): Result<UserApiModelList> {

        val response = apiEndpoints.getUsers(30, 0)

        return if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception("Failed to get users"))
        }
    }

    suspend fun getUserByUsername(username: String): Result<UserApiModel> {
        val response = apiEndpoints.getUserByUsername(username)

        return if (response.isSuccessful) {
            response.body()?.let { user ->
                Result.success(user)
            } ?: Result.failure(Exception("Failed to get user, response body was null"))
        } else {
            Result.failure(Exception("Failed to get user, response code: ${response.code()}"))
        }
    }
}
