package no.app.features.userlist

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import no.app.data.model.api.UserApiModel
import no.app.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val users: Flow<PagingData<UserApiModel>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        UserListLogSource(userRepository, PAGE_SIZE)
    }.flow

    companion object {
        const val PAGE_SIZE = 90
    }
}
