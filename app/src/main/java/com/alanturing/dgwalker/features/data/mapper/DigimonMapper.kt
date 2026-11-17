package com.alanturing.dgwalker.features.data.mapper

import com.alanturing.dgwalker.features.data.local.db.entities.LocalDigimonEntity
import com.alanturing.dgwalker.features.data.local.db.entities.LocalItemEntity
import com.alanturing.dgwalker.features.domain.model.Digimon
import com.alanturing.dgwalker.features.domain.model.InventoryItem

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