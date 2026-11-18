package com.alanturing.dgwalker.features.domain.repository

import com.alanturing.dgwalker.features.domain.model.InventoryItem
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {

    //traer datos
    fun getAllAvailableItems(): Flow<List<InventoryItem>>
    fun getItemsByCategory(category: String): Flow<List<InventoryItem>>
    fun getItemsAssignedToDigimon(digimonId: String): Flow<List<InventoryItem>>

    //operaciones locales
    suspend fun addItem(item: InventoryItem): Long
    suspend fun updateQuantity(itemId: String, quantity: Int): Boolean
    suspend fun assignItemToDigimon(itemId: String, digimonId: String?): Boolean
    suspend fun consumeItem(itemId: String): Boolean
    suspend fun deleteItem(itemId: String): Boolean

    //sincronizacion
    suspend fun syncInventoryFromRemote(): Result<Unit>
}