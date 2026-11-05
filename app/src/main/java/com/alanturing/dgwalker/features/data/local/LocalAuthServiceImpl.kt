package com.alanturing.dgwalker.features.data.local

import com.alanturing.dgwalker.features.data.model.User
import com.alanturing.dgwalker.features.domain.repository.AuthService

private data class LocalUserData(
    val uid: String,
    val username: String,
    val email: String,
    val password: String
)

private val users = mutableListOf(
    LocalUserData(uid = "1", username = "Lein", email = "test@dgwalker.com", password = "123")
)

class LocalAuthServiceImpl : AuthService {
    override suspend fun login(userOrEmail: String, pass: String): Result<User> {
        val user = users.find {
            (it.email == userOrEmail || it.username == userOrEmail) && it.password == pass
        }
        return if (user != null) {
            Result.success(User(user.uid, user.username, user.email, user.password))
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun register(
        user: String,
        email: String,
        password: String
    ): Result<User> {
        TODO("Not yet implemented")
    }
}