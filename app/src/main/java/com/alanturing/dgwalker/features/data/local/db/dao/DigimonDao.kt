package com.alanturing.dgwalker.features.data.local.db.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Transaction
import androidx.room3.Update
import com.alanturing.dgwalker.features.data.local.db.entities.LocalDigimonEntity
import com.alanturing.dgwalker.features.data.local.db.relations.DigimonWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface DigimonDao {

    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDigimon(digimon: LocalDigimonEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: List<LocalDigimonEntity>): List<Long>

    // READ
    @Query("SELECT * FROM walking_team ORDER BY slotNumber ASC")
    fun getWalkingTeam(): Flow<List<LocalDigimonEntity>>

    @Query("SELECT * FROM walking_team WHERE digimonId = :digimonId LIMIT 1")
    fun getDigimonById(digimonId: String): Flow<LocalDigimonEntity?>

    @Query("SELECT * FROM walking_team WHERE slotNumber = :slot LIMIT 1")
    suspend fun getDigimonBySlot(slot: Int): LocalDigimonEntity?

    @Transaction
    @Query("SELECT * FROM walking_team WHERE digimonId = :digimonId")
    fun getDigimonWithItems(digimonId: String): Flow<DigimonWithItems?>

    @Transaction
    @Query("SELECT * FROM walking_team ORDER BY slotNumber ASC")
    fun getWalkingTeamWithItems(): Flow<List<DigimonWithItems>>

    // UPDATE
    @Update
    suspend fun updateDigimon(digimon: LocalDigimonEntity): Int

    @Query("UPDATE walking_team SET nickname = :newNickname WHERE digimonId = :digimonId")
    suspend fun updateNickname(digimonId: String, newNickname: String): Int

    @Query("UPDATE walking_team SET hunger = :newHunger WHERE digimonId = :digimonId")
    suspend fun updateHunger(digimonId: String, newHunger: Int): Int

    @Query("UPDATE walking_team SET fatigue = :newFatigue WHERE digimonId = :digimonId")
    suspend fun updateFatigue(digimonId: String, newFatigue: Int): Int

    @Query("UPDATE walking_team SET friendship = :newFriendship WHERE digimonId = :digimonId")
    suspend fun updateFriendship(digimonId: String, newFriendship: Int): Int

    @Query("UPDATE walking_team SET stepsWalked = stepsWalked + :steps WHERE digimonId = :digimonId")
    suspend fun addSteps(digimonId: String, steps: Int): Int

    @Query("UPDATE walking_team SET currentExp = currentExp + :exp, expGained = expGained + :exp WHERE digimonId = :digimonId")
    suspend fun addExp(digimonId: String, exp: Int): Int

    @Query("UPDATE walking_team SET level = :newLevel WHERE digimonId = :digimonId")
    suspend fun updateLevel(digimonId: String, newLevel: Int): Int

    @Query("UPDATE walking_team SET isWalking = (digimonId = :digimonId)")
    suspend fun setWalkingDigimon(digimonId: String): Int

    // DELETE
    @Delete
    suspend fun deleteDigimon(digimon: LocalDigimonEntity): Int

    @Query("DELETE FROM walking_team WHERE digimonId = :digimonId")
    suspend fun deleteDigimonById(digimonId: String): Int

    @Query("DELETE FROM walking_team")
    suspend fun clearTeam(): Int
}