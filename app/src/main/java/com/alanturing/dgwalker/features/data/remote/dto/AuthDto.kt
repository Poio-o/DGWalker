package com.alanturing.dgwalker.features.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("identifier") val identifier: String,
    @SerializedName("password") val password: String
)

data class RegisterRequest(
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class UserDto(
    @SerializedName("id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("createdAt") val createdAt: String
)

data class AuthResponseDto(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("token") val token: String,
    @SerializedName("user") val user: UserDto
)

data class ProfileResponseDto(
    @SerializedName("status") val status: String,
    @SerializedName("user") val user: UserDto
)

data class ErrorResponseDto(
    @SerializedName("status") val status: String? = null,
    @SerializedName("message") val message: String
)
