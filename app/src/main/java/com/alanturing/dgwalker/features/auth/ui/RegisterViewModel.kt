package com.alanturing.dgwalker.features.auth.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.dgwalker.R
import com.alanturing.dgwalker.features.domain.repository.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    @StringRes val errorMessageResId: Int? = null,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onUsernameChanged(value: String) {
        _uiState.update { it.copy(username = value, errorMessage = null) }
    }

    fun onEmailChanged(value: String) {
        _uiState.update { it.copy(email = value, errorMessage = null) }
    }

    fun onPasswordChanged(value: String) {
        _uiState.update { it.copy(password = value, errorMessage = null) }
    }

    fun onConfirmPasswordChanged(value: String) {
        _uiState.update { it.copy(confirmPassword = value, errorMessage = null) }
    }

    fun register() {
        val state = _uiState.value

        if (state.username.isBlank() || state.email.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(errorMessageResId = R.string.fill_in_all_fields, errorMessage = null) }
            return
        }

        if (state.password != state.confirmPassword) {
            _uiState.update { it.copy(errorMessageResId = R.string.passwords_dont_match, errorMessage = null) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = authService.register(state.username, state.email, state.password)

            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageResId = R.string.user_or_email_already_exists,
                            errorMessage = null
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