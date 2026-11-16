package com.alanturing.dgwalker.features.data.remote

import com.alanturing.dgwalker.features.data.remote.dto.AuthResponseDto
import com.alanturing.dgwalker.features.data.remote.dto.LoginRequest
import com.alanturing.dgwalker.features.data.remote.dto.ProfileResponseDto
import com.alanturing.dgwalker.features.data.remote.dto.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponseDto>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponseDto>

    @GET("auth/me")
    suspend fun getProfile(): Response<ProfileResponseDto>
}
