package com.alanturing.dgwalker.features.data.remote

import com.alanturing.dgwalker.features.data.remote.dto.DigimonRemoteDto
import retrofit2.Response
import retrofit2.http.GET

interface DigimonApiService {
    @GET("digimons/mine")
    suspend fun getDigimons(): Response<List<DigimonRemoteDto>>
}