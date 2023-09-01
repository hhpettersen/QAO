package no.app.features.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.app.data.api.ApiService
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val apiService: ApiService,
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = apiService.getAllUsers()

            println("${result}")
        }
    }
}
