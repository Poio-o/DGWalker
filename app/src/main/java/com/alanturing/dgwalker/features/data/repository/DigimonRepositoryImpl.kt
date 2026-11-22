package com.alanturing.dgwalker.features.data.repository

import com.alanturing.dgwalker.features.data.local.db.dao.DigimonDao
import com.alanturing.dgwalker.features.data.mapper.toDomain
import com.alanturing.dgwalker.features.data.mapper.toEntity
import com.alanturing.dgwalker.features.data.remote.DigimonApiService
import com.alanturing.dgwalker.features.domain.model.Digimon
import com.alanturing.dgwalker.features.domain.repository.DigimonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DigimonRepositoryImpl @Inject constructor(
    private val digimonDao: DigimonDao,
    private val apiService: DigimonApiService
) : DigimonRepository {


    //Traer datos
    override fun getWalkingTeam(): Flow<List<Digimon>> =
        digimonDao.getWalkingTeam().map { list -> list.map { it.toDomain() } }

    override fun getDigimonById(id: String): Flow<Digimon?> =
        digimonDao.getDigimonById(id).map { it?.toDomain() }

    //Operaciones locales
    override suspend fun saveDigimon(digimon: Digimon): Long =
        digimonDao.insertDigimon(digimon.toEntity())

    override suspend fun updateNickname(id: String, nickname: String): Boolean =
        digimonDao.updateNickname(id, nickname) > 0

    override suspend fun updateHunger(id: String, newHunger: Int): Boolean =
        digimonDao.updateHunger(id, newHunger.coerceIn(0, 100)) > 0

    override suspend fun updateFatigue(id: String, newFatigue: Int): Boolean =
        digimonDao.updateFatigue(id, newFatigue.coerceIn(0, 100)) > 0

    override suspend fun updateFriendship(id: String, newFriendship: Int): Boolean =
        digimonDao.updateFriendship(id, newFriendship.coerceIn(0, 100)) > 0

    override suspend fun addSteps(id: String, steps: Int): Boolean =
        digimonDao.addSteps(id, steps) > 0

    override suspend fun addExp(id: String, exp: Int): Boolean =
        digimonDao.addExp(id, exp) > 0

    override suspend fun updateLevel(id: String, level: Int): Boolean =
        digimonDao.updateLevel(id, level) > 0

    override suspend fun setWalkingDigimon(id: String): Boolean =
        digimonDao.setWalkingDigimon(id) > 0

    override suspend fun deleteDigimon(id: String): Boolean =
        digimonDao.deleteDigimonById(id) > 0

    //sincronizacion

    override suspend fun syncTeamFromRemote(): Result<Unit> = runCatching {
        val response = apiService.getDigimons()
        check(response.isSuccessful) { "Error al sincronizar digimons: ${response.code()}" }

        val teamEntities = response.body()
            .orEmpty()
            .filter { it.isWalking }
            .take(4)
            .mapIndexed { index, dto -> dto.toEntity(slot = index + 1) }

        if (teamEntities.isNotEmpty()) {
            digimonDao.insertTeam(teamEntities)
        }
    }

}