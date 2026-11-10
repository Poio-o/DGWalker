package com.alanturing.dgwalker.features.data.repository

import com.alanturing.dgwalker.features.data.local.TokenManager
import com.alanturing.dgwalker.features.data.model.User
import com.alanturing.dgwalker.features.data.remote.AuthApiService
import com.alanturing.dgwalker.features.data.remote.dto.AuthResponseDto
import com.alanturing.dgwalker.features.data.remote.dto.ErrorResponseDto
import com.alanturing.dgwalker.features.data.remote.dto.LoginRequest
import com.alanturing.dgwalker.features.data.remote.dto.RegisterRequest
import com.alanturing.dgwalker.features.data.remote.dto.UserDto
import com.alanturing.dgwalker.features.domain.repository.AuthService
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val apiService: AuthApiService,
    private val tokenManager: TokenManager,
    private val gson: Gson
) : AuthService {

    override suspend fun login(userOrEmail: String, pass: String): Result<User> {
        return try {
            val response = apiService.login(LoginRequest(identifier = userOrEmail, password = pass))
            handleAuthResponse(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(user: String, email: String, password: String): Result<User> {
        return try {
            val response = apiService.register(RegisterRequest(username = user, email = email, password = password))
            handleAuthResponse(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProfile(): Result<User> {
        return try {
            val response = apiService.getProfile()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    tokenManager.saveUser(body.user)
                    Result.success(body.user.toDomain())
                } else {
                    Result.failure(Exception("Respuesta vacía del servidor"))
                }
            } else {
                val errorMessage = parseErrorMessage(response)
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun handleAuthResponse(response: Response<AuthResponseDto>): Result<User> {
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                tokenManager.saveToken(body.token)
                tokenManager.saveUser(body.user)
                Result.success(body.user.toDomain())
            } else {
                Result.failure(Exception("Respuesta vacía del servidor"))
            }
        } else {
            val errorMessage = parseErrorMessage(response)
            Result.failure(Exception(errorMessage))
        }
    }

    private fun <T> parseErrorMessage(response: Response<T>): String {
        val errorJson = response.errorBody()?.string()
        return if (!errorJson.isNullOrEmpty()) {
            try {
                val errorDto = gson.fromJson(errorJson, ErrorResponseDto::class.java)
                errorDto.message.ifEmpty { "Error desconocido (${response.code()})" }
            } catch (_: Exception) {
                "Error (${response.code()}): ${response.message()}"
            }
        } else {
            "Error (${response.code()}): ${response.message()}"
        }
    }

    private fun UserDto.toDomain(): User {
        return User(
            id = id,
            username = username,
            email = email,
            createdAt = createdAt
        )
    }
}
