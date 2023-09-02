package no.app.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import no.app.features.util.UiState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val uiState: StateFlow<UiState<Unit>> = _uiState

    private val _uiEffects = MutableSharedFlow<UiEffect>(extraBufferCapacity = 100)
    val uiEffects: Flow<UiEffect> get() = _uiEffects.asSharedFlow()

    init {
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.currentUser

        if (currentUser != null) {
            emitUiEffect(UiEffect.Authenticated)
        } else {
            _uiState.value = UiState.Success(Unit)
        }
    }

    fun onError(error: Exception) {
        _uiState.value = UiState.Error(error)
    }

    fun onAuthenticated() {
        emitUiEffect(UiEffect.Authenticated)
    }

    private fun emitUiEffect(uiEffect: UiEffect) {
        viewModelScope.launch {
            _uiEffects.emit(uiEffect)
        }
    }
}

sealed class UiEffect {
    object Authenticated : UiEffect()
}
