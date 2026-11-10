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

data class LoginUiState(
    val userOrEmail: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    @StringRes val errorMessageResId: Int? = null,
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
        _uiState.update { it.copy(userOrEmail = input, errorMessageResId = null, errorMessage = null) }
    }

    fun onPasswordChanged(input: String) {
        _uiState.update { it.copy(password = input, errorMessageResId = null, errorMessage = null) }
    }

    fun onLogin(identifier: String = _uiState.value.userOrEmail, pass: String = _uiState.value.password) {
        if (identifier.isBlank() || pass.isBlank()) {
            _uiState.update { it.copy(errorMessageResId = R.string.fill_in_both_fields, errorMessage = null) }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessageResId = null, errorMessage = null) }
            val result = authService.login(identifier, pass)
            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageResId = null,
                            errorMessage = error.message ?: "Credenciales incorrectas"
                        )
                    }
                }
            )
        }
    }

    fun login() = onLogin()

    fun resetSuccessState() {
        _uiState.update { it.copy(isSuccess = false) }
    }
}
