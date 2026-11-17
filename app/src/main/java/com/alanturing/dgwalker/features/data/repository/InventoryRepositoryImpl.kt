package com.alanturing.dgwalker.features.data.repository

import com.alanturing.dgwalker.features.data.local.db.dao.ItemDao
import com.alanturing.dgwalker.features.data.mapper.toDomain
import com.alanturing.dgwalker.features.data.mapper.toEntity
import com.alanturing.dgwalker.features.domain.model.InventoryItem
import com.alanturing.dgwalker.features.domain.repository.InventoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InventoryRepositoryImpl @Inject constructor(
    private val itemDao: ItemDao
) : InventoryRepository {

    override fun getAllAvailableItems(): Flow<List<InventoryItem>> =
        itemDao.getAllAvailableItems().map { list -> list.map { it.toDomain() } }

    override fun getItemsByCategory(category: String): Flow<List<InventoryItem>> =
        itemDao.getItemsByCategory(category).map { list -> list.map { it.toDomain() } }

    override fun getItemsAssignedToDigimon(digimonId: String): Flow<List<InventoryItem>> =
        itemDao.getItemsAssignedToDigimon(digimonId).map { list -> list.map { it.toDomain() } }

    override suspend fun addItem(item: InventoryItem): Long =
        itemDao.insertItem(item.toEntity())

    override suspend fun updateQuantity(itemId: String, quantity: Int): Boolean =
        itemDao.updateQuantity(itemId, quantity) > 0

    override suspend fun assignItemToDigimon(itemId: String, digimonId: String?): Boolean =
        itemDao.assignItemToDigimon(itemId, digimonId) > 0

    override suspend fun consumeItem(itemId: String): Boolean {
        val item = itemDao.getItemById(itemId) ?: return false
        return if (item.quantity > 1) {
            itemDao.updateQuantity(itemId, item.quantity - 1) > 0
        } else {
            itemDao.deleteItemById(itemId) > 0
        }
    }

    override suspend fun deleteItem(itemId: String): Boolean =
        itemDao.deleteItemById(itemId) > 0
}