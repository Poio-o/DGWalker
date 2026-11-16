package com.alanturing.dgwalker.features.data.local.db.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Update
import com.alanturing.dgwalker.features.data.local.db.entities.LocalItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: LocalItemEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<LocalItemEntity>): List<Long>

    // READ
    @Query("SELECT * FROM local_inventory WHERE quantity > 0 ORDER BY category ASC, name ASC")
    fun getAllAvailableItems(): Flow<List<LocalItemEntity>>

    @Query("SELECT * FROM local_inventory WHERE category = :category AND quantity > 0")
    fun getItemsByCategory(category: String): Flow<List<LocalItemEntity>>

    @Query("SELECT * FROM local_inventory WHERE itemId = :itemId LIMIT 1")
    suspend fun getItemById(itemId: String): LocalItemEntity?

    @Query("SELECT * FROM local_inventory WHERE assignedDigimonId = :digimonId")
    fun getItemsAssignedToDigimon(digimonId: String): Flow<List<LocalItemEntity>>

    // UPDATE
    @Update
    suspend fun updateItem(item: LocalItemEntity): Int

    @Query("UPDATE local_inventory SET quantity = :newQuantity WHERE itemId = :itemId")
    suspend fun updateQuantity(itemId: String, newQuantity: Int): Int

    @Query("UPDATE local_inventory SET assignedDigimonId = :digimonId WHERE itemId = :itemId")
    suspend fun assignItemToDigimon(itemId: String, digimonId: String?): Int

    // DELETE
    @Delete
    suspend fun deleteItem(item: LocalItemEntity): Int

    @Query("DELETE FROM local_inventory WHERE itemId = :itemId")
    suspend fun deleteItemById(itemId: String): Int

    @Query("DELETE FROM local_inventory WHERE quantity <= 0")
    suspend fun deleteExhaustedItems(): Int
}