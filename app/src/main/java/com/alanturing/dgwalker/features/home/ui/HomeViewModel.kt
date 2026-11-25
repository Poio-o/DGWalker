package com.alanturing.dgwalker.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.dgwalker.features.data.local.TokenManager
import com.alanturing.dgwalker.features.domain.model.Digimon
import com.alanturing.dgwalker.features.domain.model.InventoryItem
import com.alanturing.dgwalker.features.domain.repository.DigimonRepository
import com.alanturing.dgwalker.features.domain.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true,
    val team: List<Digimon> = emptyList(),
    val inventory: List<InventoryItem> = emptyList(),
    val userSteps: Int = 0,
    val userMessage: String? = null,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val digimonRepository: DigimonRepository,
    private val inventoryRepository: InventoryRepository,
    private val tokenManager: TokenManager? = null
) : ViewModel() {

    private val _userMessage = MutableStateFlow<String?>(null)
    private val _error = MutableStateFlow<String?>(null)

    init {
        syncFromRemote()
    }

    val uiState: StateFlow<HomeUiState> = combine(
        digimonRepository.getWalkingTeam(),
        inventoryRepository.getAllAvailableItems(),
        tokenManager?.userStepsFlow ?: MutableStateFlow(0),
        _userMessage,
        _error
    ) { team, inventory, steps, message, error ->
        HomeUiState(
            isLoading = false,
            team = team,
            inventory = inventory,
            userSteps = steps,
            userMessage = message,
            error = error
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState(isLoading = true)
    )

    fun feedDigimon(digimonId: String, item: InventoryItem) {
        viewModelScope.launch {
            val currentTeam = uiState.value.team
            val targetDigimon = currentTeam.find { it.id == digimonId } ?: return@launch

            val newHunger = (targetDigimon.hunger - item.hungerRestore).coerceIn(0, 100)
            val newFriendship = (targetDigimon.friendship + item.friendshipGain).coerceIn(0, 100)

            val hungerUpdated = digimonRepository.updateHunger(digimonId, newHunger)
            val friendshipUpdated = digimonRepository.updateFriendship(digimonId, newFriendship)
            val itemConsumed = inventoryRepository.consumeItem(item.id)

            if (hungerUpdated && friendshipUpdated && itemConsumed) {
                _userMessage.value = "¡Has alimentado a ${targetDigimon.nickname} con ${item.name}!"
            } else {
                _error.value = "No se pudo alimentar al Digimon"
            }
        }
    }

    fun updateNickname(digimonId: String, newNickname: String) {
        if (newNickname.isBlank()) {
            _error.value = "El apodo no puede estar vacío"
            return
        }
        viewModelScope.launch {
            val success = digimonRepository.updateNickname(digimonId, newNickname.trim())
            if (success) {
                _userMessage.value = "Apodo actualizado correctamente"
            } else {
                _error.value = "Error al actualizar el apodo"
            }
        }
    }

    fun setWalkingDigimon(digimonId: String) {
        viewModelScope.launch {
            val success = digimonRepository.setWalkingDigimon(digimonId)
            val target = uiState.value.team.find { it.id == digimonId }
            if (success) {
                _userMessage.value = "${target?.nickname ?: "Digimon"} es ahora tu compañero activo"
            } else {
                _error.value = "Error al activar el Digimon"
            }
        }
    }

    fun syncFromRemote() {
        viewModelScope.launch {
            runCatching {
                digimonRepository.syncTeamFromRemote()
                inventoryRepository.syncInventoryFromRemote()
            }
        }
    }

    fun clearUserMessage() {
        _userMessage.value = null
    }

    fun clearError() {
        _error.value = null
    }
}
