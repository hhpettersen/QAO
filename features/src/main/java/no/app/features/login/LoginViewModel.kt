package no.app.features.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import no.app.data.store.KeyStore
import no.app.features.util.UiState
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val keyStore: KeyStore,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<LoginState>>(UiState.Loading)
    val uiState: StateFlow<UiState<LoginState>> = _uiState

    init {
        if (keyStore.getLoginSession() != null) {
            _uiState.value = UiState.Success(LoginState(isAuthenticated = true))
        } else {
            _uiState.value = UiState.Success(LoginState(isAuthenticated = false))
        }
    }

    fun onError(error: Exception) {
        _uiState.value = UiState.Error(error)
    }

    fun onAuthenticated() {
        keyStore.saveLoginSession(UUID.randomUUID().toString())
        _uiState.value = UiState.Success(LoginState(isAuthenticated = true))
    }
}

data class LoginState(
    val isAuthenticated: Boolean = false,
)
