package com.alanturing.dgwalker.features.data.remote

import com.alanturing.dgwalker.features.data.remote.dto.InventoryRemoteDto
import retrofit2.Response
import retrofit2.http.GET

interface InventoryApiService {
    @GET("inventory/mine")
    suspend fun getInventory(): Response<List<InventoryRemoteDto>>
}