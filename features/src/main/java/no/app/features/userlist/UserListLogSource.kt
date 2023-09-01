package no.app.features.userlist

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import no.app.data.model.api.UserApiModel
import no.app.data.model.api.UserApiModelList
import no.app.data.repository.UserRepository
import no.app.data.utils.ApiResult

class UserListLogSource(
    private val repository: UserRepository,
    private val pageSize: Int,
) : PagingSource<Int, UserApiModel>() {

    // User as a key to only return users with an ID greater than this ID.
    private var lastUserId: Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserApiModel> {
        return withContext(Dispatchers.IO) {
            try {
                val nextPageNumber = params.key ?: 1

                val response = repository.getAllUsers(
                    perPage = pageSize,
                    since = lastUserId ?: 0,
                ) as? ApiResult.Success<UserApiModelList> ?: return@withContext LoadResult.Error(
                    Exception("Failed to get users")
                )

                val users = response.data

                lastUserId = users.lastOrNull()?.id

                LoadResult.Page(
                    data = response.data,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (response.data.isEmpty()) null else nextPageNumber + 1,
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }

    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, UserApiModel>): Int {
        return ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)
    }
}