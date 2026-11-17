package com.alanturing.dgwalker.features.domain.model

data class InventoryItem(
    val id: String,
    val name: String,
    val category: String,
    val quantity: Int,
    val iconUrl: String,
    val description: String,
    val hungerRestore: Int,
    val friendshipGain: Int,
    val assignedDigimonId: String? = null
)