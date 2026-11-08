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
        val userExists = users.any {
            it.username.equals(user, ignoreCase = true) || it.email.equals(email, ignoreCase = true)
        }

        return if (userExists) {
            Result.failure(Exception("El usuario o correo electrónico ya está registrado"))
        } else {
            val newUser = LocalUserData(
                uid = (users.size + 1).toString(),
                username = user,
                email = email,
                password = password
            )
            users.add(newUser)
            Result.success(User(newUser.uid, newUser.username, newUser.email, newUser.password))
        }
    }
}