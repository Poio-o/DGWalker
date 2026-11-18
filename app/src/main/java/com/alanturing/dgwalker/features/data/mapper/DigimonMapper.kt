package com.alanturing.dgwalker.features.data.mapper

import com.alanturing.dgwalker.features.data.local.db.entities.LocalDigimonEntity
import com.alanturing.dgwalker.features.data.local.db.entities.LocalItemEntity
import com.alanturing.dgwalker.features.domain.model.Digimon
import com.alanturing.dgwalker.features.domain.model.InventoryItem
import com.alanturing.dgwalker.features.data.remote.dto.DigimonRemoteDto
import com.alanturing.dgwalker.features.data.remote.dto.InventoryRemoteDto

fun LocalDigimonEntity.toDomain(): Digimon = Digimon(
    id = digimonId,
    slotNumber = slotNumber,
    nickname = nickname,
    speciesName = speciesName,
    generation = generation,
    attribute = attribute,
    type = type,
    spriteUrl = spriteUrl,
    level = level,
    currentExp = currentExp,
    friendship = friendship,
    hunger = hunger,
    fatigue = fatigue,
    baseHp = baseHp,
    baseSp = baseSp,
    baseAtk = baseAtk,
    baseDef = baseDef,
    baseInt = baseInt,
    baseSpi = baseSpi,
    baseSpd = baseSpd,
    npHp = npHp,
    npSp = npSp,
    npAtk = npAtk,
    npDef = npDef,
    npInt = npInt,
    npSpi = npSpi,
    npSpd = npSpd,
    bonusHp = bonusHp,
    bonusSp = bonusSp,
    bonusAtk = bonusAtk,
    bonusDef = bonusDef,
    bonusInt = bonusInt,
    bonusSpi = bonusSpi,
    bonusSpd = bonusSpd,
    stepsWalked = stepsWalked,
    expGained = expGained,
    friendshipGained = friendshipGained,
    isWalking = isWalking
)

fun Digimon.toEntity(): LocalDigimonEntity = LocalDigimonEntity(
    digimonId = id,
    slotNumber = slotNumber,
    nickname = nickname,
    speciesName = speciesName,
    generation = generation,
    attribute = attribute,
    type = type,
    spriteUrl = spriteUrl,
    level = level,
    currentExp = currentExp,
    friendship = friendship,
    hunger = hunger,
    fatigue = fatigue,
    baseHp = baseHp,
    baseSp = baseSp,
    baseAtk = baseAtk,
    baseDef = baseDef,
    baseInt = baseInt,
    baseSpi = baseSpi,
    baseSpd = baseSpd,
    npHp = npHp,
    npSp = npSp,
    npAtk = npAtk,
    npDef = npDef,
    npInt = npInt,
    npSpi = npSpi,
    npSpd = npSpd,
    bonusHp = bonusHp,
    bonusSp = bonusSp,
    bonusAtk = bonusAtk,
    bonusDef = bonusDef,
    bonusInt = bonusInt,
    bonusSpi = bonusSpi,
    bonusSpd = bonusSpd,
    stepsWalked = stepsWalked,
    expGained = expGained,
    friendshipGained = friendshipGained,
    isWalking = isWalking
)

fun LocalItemEntity.toDomain(): InventoryItem = InventoryItem(
    id = itemId,
    name = name,
    category = category,
    quantity = quantity,
    iconUrl = iconUrl,
    description = description,
    hungerRestore = hungerRestore,
    friendshipGain = friendshipGain,
    assignedDigimonId = assignedDigimonId
)

fun InventoryItem.toEntity(): LocalItemEntity = LocalItemEntity(
    itemId = id,
    name = name,
    category = category,
    quantity = quantity,
    iconUrl = iconUrl,
    description = description,
    hungerRestore = hungerRestore,
    friendshipGain = friendshipGain,
    assignedDigimonId = assignedDigimonId
)

// DTO

fun DigimonRemoteDto.toEntity(slot: Int): LocalDigimonEntity =
    LocalDigimonEntity(
        digimonId = digimonId,
        slotNumber = slot,
        nickname = nickname,
        speciesName = species?.name ?: "Unknown Digimon",
        generation = species?.generation ?: "Rookie",
        attribute = species?.attribute ?: "Free",
        type = species?.type ?: "Unknown",
        spriteUrl = species?.spriteUrl ?: "",
        level = level,
        currentExp = currentExp,
        friendship = friendship,
        hunger = hunger,
        fatigue = fatigue,
        baseHp = baseHp,
        baseSp = baseSp,
        baseAtk = baseAtk,
        baseDef = baseDef,
        baseInt = baseInt,
        baseSpi = baseSpi,
        baseSpd = baseSpd,
        npHp = npHp,
        npSp = npSp,
        npAtk = npAtk,
        npDef = npDef,
        npInt = npInt,
        npSpi = npSpi,
        npSpd = npSpd,
        bonusHp = bonusHp,
        bonusSp = bonusSp,
        bonusAtk = bonusAtk,
        bonusDef = bonusDef,
        bonusInt = bonusInt,
        bonusSpi = bonusSpi,
        bonusSpd = bonusSpd,
        stepsWalked = 0,
        expGained = 0,
        friendshipGained = 0,
        isWalking = isWalking
    )

fun InventoryRemoteDto.toEntity(): LocalItemEntity =
    LocalItemEntity(
        itemId = itemId,
        name = itemDetail?.name ?: itemId,
        category = itemDetail?.category ?: "FOOD",
        quantity = quantity,
        iconUrl = itemDetail?.iconUrl ?: "",
        description = itemDetail?.description ?: "",
        hungerRestore = itemDetail?.hungerRestored ?: 0,
        friendshipGain = itemDetail?.friendshipGain ?: 0,
        assignedDigimonId = assignedDigimonId
    )