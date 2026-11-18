package com.alanturing.dgwalker.features.domain.repository

import com.alanturing.dgwalker.features.domain.model.Digimon
import kotlinx.coroutines.flow.Flow

interface DigimonRepository {

    //traer datos
    fun getWalkingTeam(): Flow<List<Digimon>>
    fun getDigimonById(id: String): Flow<Digimon?>

    //operaciones locales
    suspend fun saveDigimon(digimon: Digimon): Long
    suspend fun updateNickname(id: String, nickname: String): Boolean
    suspend fun updateHunger(id: String, newHunger: Int): Boolean
    suspend fun updateFatigue(id: String, newFatigue: Int): Boolean
    suspend fun updateFriendship(id: String, newFriendship: Int): Boolean
    suspend fun addSteps(id: String, steps: Int): Boolean
    suspend fun addExp(id: String, exp: Int): Boolean
    suspend fun updateLevel(id: String, level: Int): Boolean
    suspend fun deleteDigimon(id: String): Boolean

    //sincronizacion
    suspend fun syncTeamFromRemote(): Result<Unit>
}

