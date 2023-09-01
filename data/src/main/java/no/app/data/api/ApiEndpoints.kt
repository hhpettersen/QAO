package no.app.data.api

import no.app.data.model.api.UserApiModel
import no.app.data.model.api.UserApiModelList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoints {
    @GET("users")
    suspend fun getUsers(
        @Query("per_page") pageSize: Int,
        @Query("since") since: Int,
    ): Response<UserApiModelList>

    @GET("users/{username}")
    suspend fun getUserByUsername(username: String): Response<UserApiModel>
}
