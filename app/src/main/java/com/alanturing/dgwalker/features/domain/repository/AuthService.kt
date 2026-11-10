package com.alanturing.dgwalker.features.domain.repository

import com.alanturing.dgwalker.features.data.model.User

interface AuthService {
    suspend fun login(userOrEmail: String, pass: String): Result<User>
    suspend fun register(user: String, email: String, password: String): Result<User>
    suspend fun getProfile(): Result<User>
}
