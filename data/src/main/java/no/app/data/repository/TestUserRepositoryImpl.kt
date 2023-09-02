package no.app.data.repository

import no.app.data.model.api.UserApiModelList
import no.app.data.model.api.UserDetailApiModel
import no.app.data.utils.ApiResult

class TestUserRepositoryImpl(
    private val users: UserApiModelList,
    private val user: UserDetailApiModel,
) : UserRepository {
    override suspend fun getAllUsers(perPage: Int, since: Int): ApiResult<UserApiModelList> {
        return ApiResult.Success(users)
    }

    override suspend fun getUserByUsername(username: String): ApiResult<UserDetailApiModel> {
        return ApiResult.Success(user)
    }
}
