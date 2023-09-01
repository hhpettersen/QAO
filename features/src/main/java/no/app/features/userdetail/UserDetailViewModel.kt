package no.app.features.userdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import no.app.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    repository: UserRepository,
    handle: SavedStateHandle,
): ViewModel() {
    init {
        val userId = handle.get<Int>("userId")
    }
}
