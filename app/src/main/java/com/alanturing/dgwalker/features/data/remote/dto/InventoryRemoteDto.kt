package com.alanturing.dgwalker.features.data.remote.dto

import com.google.gson.annotations.SerializedName

data class InventoryRemoteDto(
    @SerializedName("itemId") val itemId: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("assignedDigimonId") val assignedDigimonId: String? = null,
    @SerializedName("item") val itemDetail: ItemDetailRemoteDto? = null
)
data class ItemDetailRemoteDto(
    @SerializedName("name") val name: String,
    @SerializedName("category") val category: String,
    @SerializedName("description") val description: String,
    @SerializedName("iconUrl") val iconUrl: String,
    @SerializedName("hungerRestored") val hungerRestored: Int = 0,
    @SerializedName("friendshipGain") val friendshipGain: Int = 0
)