package com.alanturing.dgwalker.features.home.ui

import com.alanturing.dgwalker.features.domain.model.Digimon
import com.alanturing.dgwalker.features.domain.model.InventoryItem
import com.alanturing.dgwalker.features.domain.repository.DigimonRepository
import com.alanturing.dgwalker.features.domain.repository.InventoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var fakeDigimonRepository: FakeDigimonRepository
    private lateinit var fakeInventoryRepository: FakeInventoryRepository
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeDigimonRepository = FakeDigimonRepository()
        fakeInventoryRepository = FakeInventoryRepository()
        viewModel = HomeViewModel(fakeDigimonRepository, fakeInventoryRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun uiState_initialEmitsTeamAndInventory() = runTest {
        backgroundScope.launch { viewModel.uiState.collect() }
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertEquals(1, state.team.size)
        assertEquals("Agumon", state.team.first().nickname)
        assertEquals(1, state.inventory.size)
        assertEquals("Manzana Digimon", state.inventory.first().name)
    }

    @Test
    fun feedDigimon_updatesHungerAndFriendshipAndConsumesItem() = runTest {
        backgroundScope.launch { viewModel.uiState.collect() }
        testDispatcher.scheduler.advanceUntilIdle()

        val item = fakeInventoryRepository.itemsFlow.value.first()
        viewModel.feedDigimon("1", item)
        testDispatcher.scheduler.advanceUntilIdle()

        val updatedDigimon = fakeDigimonRepository.teamFlow.value.first()
        assertEquals(30, updatedDigimon.hunger)
        assertEquals(60, updatedDigimon.friendship)
        assertEquals(0, fakeInventoryRepository.itemsFlow.value.size)

        val state = viewModel.uiState.value
        assertNotNull(state.userMessage)
    }

    @Test
    fun updateNickname_updatesNameSuccessfully() = runTest {
        backgroundScope.launch { viewModel.uiState.collect() }
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.updateNickname("1", "WarGreymon")
        testDispatcher.scheduler.advanceUntilIdle()

        val updatedDigimon = fakeDigimonRepository.teamFlow.value.first()
        assertEquals("WarGreymon", updatedDigimon.nickname)
    }

    @Test
    fun setWalkingDigimon_updatesActiveDigimon() = runTest {
        backgroundScope.launch { viewModel.uiState.collect() }
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.setWalkingDigimon("1")
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertNotNull(state.userMessage)
    }
}

private class FakeDigimonRepository : DigimonRepository {
    val teamFlow = MutableStateFlow(
        listOf(
            Digimon(
                id = "1",
                slotNumber = 1,
                nickname = "Agumon",
                speciesName = "Agumon",
                generation = "Rookie",
                attribute = "Vaccine",
                type = "Reptile",
                spriteUrl = "",
                level = 10,
                currentExp = 100,
                friendship = 50,
                hunger = 50,
                fatigue = 0,
                baseHp = 10, baseSp = 10, baseAtk = 10, baseDef = 10, baseInt = 10, baseSpi = 10, baseSpd = 10,
                npHp = 1, npSp = 1, npAtk = 1, npDef = 1, npInt = 1, npSpi = 1, npSpd = 1,
                bonusHp = 0, bonusSp = 0, bonusAtk = 0, bonusDef = 0, bonusInt = 0, bonusSpi = 0, bonusSpd = 0,
                stepsWalked = 500, expGained = 50, friendshipGained = 5, isWalking = true
            )
        )
    )

    override fun getWalkingTeam(): Flow<List<Digimon>> = teamFlow
    override fun getDigimonById(id: String): Flow<Digimon?> = MutableStateFlow(teamFlow.value.find { it.id == id })

    override suspend fun saveDigimon(digimon: Digimon): Long = 1L

    override suspend fun updateNickname(id: String, nickname: String): Boolean {
        teamFlow.value = teamFlow.value.map { if (it.id == id) it.copy(nickname = nickname) else it }
        return true
    }

    override suspend fun updateHunger(id: String, newHunger: Int): Boolean {
        teamFlow.value = teamFlow.value.map { if (it.id == id) it.copy(hunger = newHunger) else it }
        return true
    }

    override suspend fun updateFriendship(id: String, newFriendship: Int): Boolean {
        teamFlow.value = teamFlow.value.map { if (it.id == id) it.copy(friendship = newFriendship) else it }
        return true
    }

    override suspend fun updateFatigue(id: String, newFatigue: Int): Boolean = true
    override suspend fun addSteps(id: String, steps: Int): Boolean = true
    override suspend fun addExp(id: String, exp: Int): Boolean = true
    override suspend fun updateLevel(id: String, level: Int): Boolean = true
    override suspend fun setWalkingDigimon(id: String): Boolean = true

    override suspend fun deleteDigimon(id: String): Boolean {
        teamFlow.value = teamFlow.value.filterNot { it.id == id }
        return true
    }

    override suspend fun syncTeamFromRemote(): Result<Unit> = Result.success(Unit)
}

private class FakeInventoryRepository : InventoryRepository {
    val itemsFlow = MutableStateFlow(
        listOf(
            InventoryItem(
                id = "item_1",
                name = "Manzana Digimon",
                category = "Food",
                quantity = 1,
                iconUrl = "",
                description = "Restaura hambre",
                hungerRestore = 20,
                friendshipGain = 10
            )
        )
    )

    override fun getAllAvailableItems(): Flow<List<InventoryItem>> = itemsFlow
    override fun getItemsByCategory(category: String): Flow<List<InventoryItem>> = itemsFlow
    override fun getItemsAssignedToDigimon(digimonId: String): Flow<List<InventoryItem>> = itemsFlow

    override suspend fun addItem(item: InventoryItem): Long = 1L
    override suspend fun updateQuantity(itemId: String, quantity: Int): Boolean = true
    override suspend fun assignItemToDigimon(itemId: String, digimonId: String?): Boolean = true

    override suspend fun consumeItem(itemId: String): Boolean {
        itemsFlow.value = itemsFlow.value.filterNot { it.id == itemId }
        return true
    }

    override suspend fun deleteItem(itemId: String): Boolean = true
    override suspend fun syncInventoryFromRemote(): Result<Unit> = Result.success(Unit)
}
