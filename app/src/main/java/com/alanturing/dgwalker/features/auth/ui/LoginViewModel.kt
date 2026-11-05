package com.alanturing.dgwalker.features.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.dgwalker.features.domain.repository.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val userOrEmail: String = "",
    val password: String ="",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()
    fun onUserOrEmailChanged(input: String) {
        _uiState.update { it.copy(userOrEmail = input, errorMessage = null) }
    }
    fun onPasswordChanged(input: String) {
        _uiState.update { it.copy(password = input, errorMessage = null) }
    }
    fun login() {
        val currentState = _uiState.value
        if (currentState.userOrEmail.isBlank() || currentState.password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Please enter both fields.") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = authService.login(currentState.userOrEmail, currentState.password)
            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.localizedMessage ?: "Invalid credentials"
                        )
                    }
                }
            )
        }
    }

    fun resetSuccessState() {
        _uiState.update { it.copy(isSuccess = false) }
    }
}