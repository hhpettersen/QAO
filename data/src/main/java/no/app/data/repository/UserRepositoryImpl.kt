package no.app.data.repository

import no.app.data.api.ApiService
import no.app.data.db.AppDatabase
import no.app.data.model.api.UserApiModelList
import no.app.data.model.api.UserDetailApiModel
import no.app.data.utils.ApiResult
import no.app.data.utils.toApiModel
import no.app.data.utils.toEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: AppDatabase
) : UserRepository {

    override suspend fun getAllUsers(perPage: Int, since: Int): ApiResult<UserApiModelList> {
        return apiService.getAllUsers(perPage, since)
    }

    override suspend fun getUserByUsername(username: String): ApiResult<UserDetailApiModel> {
        val cachedUser = database.userDetailDao().getUserByUsername(username)
        return if (cachedUser != null) {
            ApiResult.Success(cachedUser.toApiModel())
        } else {
            when (val result = apiService.getUserByUsername(username)) {
                is ApiResult.Success -> {
                    database.userDetailDao().insert(result.data.toEntity())
                    result
                }
                is ApiResult.Error -> result
            }
        }
    }
}
