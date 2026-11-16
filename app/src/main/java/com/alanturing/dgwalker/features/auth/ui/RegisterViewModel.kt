package com.alanturing.dgwalker.features.auth.ui

import android.util.Patterns
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
        _uiState.update { it.copy(username = value, errorMessageResId = null, errorMessage = null) }
    }

    fun onEmailChanged(value: String) {
        _uiState.update { it.copy(email = value, errorMessageResId = null, errorMessage = null) }
    }

    fun onPasswordChanged(value: String) {
        _uiState.update { it.copy(password = value, errorMessageResId = null, errorMessage = null) }
    }

    fun onConfirmPasswordChanged(value: String) {
        _uiState.update { it.copy(confirmPassword = value, errorMessageResId = null, errorMessage = null) }
    }

    fun onRegister(
        username: String = _uiState.value.username,
        email: String = _uiState.value.email,
        password: String = _uiState.value.password
    ) {
        val state = _uiState.value

        if (username.isBlank() || email.isBlank() || password.isBlank()) {
            _uiState.update { it.copy(errorMessageResId = R.string.fill_in_all_fields, errorMessage = null) }
            return
        }

        if (username.length < 3) {
            _uiState.update { it.copy(errorMessageResId = R.string.username_too_short, errorMessage = null) }
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _uiState.update { it.copy(errorMessageResId = R.string.invalid_email_format, errorMessage = null) }
            return
        }

        if (password.length < 6) {
            _uiState.update { it.copy(errorMessageResId = R.string.password_too_short, errorMessage = null) }
            return
        }

        if (password != state.confirmPassword && state.confirmPassword.isNotEmpty()) {
            _uiState.update { it.copy(errorMessageResId = R.string.passwords_dont_match, errorMessage = null) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessageResId = null, errorMessage = null) }

            val result = authService.register(username, email, password)

            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageResId = null,
                            errorMessage = error.message ?: "Error al registrar usuario"
                        )
                    }
                }
            )
        }
    }

    fun register() = onRegister()

    fun resetSuccessState() {
        _uiState.update { it.copy(isSuccess = false) }
    }
}
