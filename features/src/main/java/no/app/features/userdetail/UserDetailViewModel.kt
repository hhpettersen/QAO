package no.app.features.userdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import no.app.data.model.api.UserDetailApiModel
import no.app.data.repository.UserRepository
import no.app.data.utils.ApiResult
import no.app.features.util.UiState
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val repository: UserRepository,
    handle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<UserDetailApiModel>>(UiState.Loading)
    val uiState: StateFlow<UiState<UserDetailApiModel>> = _uiState

    init {
        handle.get<String>("username")?.let {
            getUser(it)
        }
    }

    private fun getUser(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getUserByUsername(username)) {
                is ApiResult.Success -> {
                    _uiState.value = UiState.Success(result.data)
                }
                is ApiResult.Error -> {
                    _uiState.value = UiState.Error(result.exception)
                }
            }
        }
    }
}
