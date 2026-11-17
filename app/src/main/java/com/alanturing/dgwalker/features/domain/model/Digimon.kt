package com.alanturing.dgwalker.features.domain.model

data class Digimon(
    val id: String,
    val slotNumber: Int,            // 1 a 4
    val nickname: String,
    val speciesName: String,
    val generation: String,
    val attribute: String,
    val type: String,
    val spriteUrl: String,
    val level: Int,
    val currentExp: Int,
    val friendship: Int,
    val hunger: Int,
    val fatigue: Int,

    // 7 Stats Base
    val baseHp: Int,
    val baseSp: Int,
    val baseAtk: Int,
    val baseDef: Int,
    val baseInt: Int,
    val baseSpi: Int,
    val baseSpd: Int,

    // Natural Power 1 a 30
    val npHp: Int,
    val npSp: Int,
    val npAtk: Int,
    val npDef: Int,
    val npInt: Int,
    val npSpi: Int,
    val npSpd: Int,

    // Bonos de entrenamiento
    val bonusHp: Int,
    val bonusSp: Int,
    val bonusAtk: Int,
    val bonusDef: Int,
    val bonusInt: Int,
    val bonusSpi: Int,
    val bonusSpd: Int,

    // Progreso del paseo
    val stepsWalked: Int,
    val expGained: Int,
    val friendshipGained: Int,
    val isWalking: Boolean
) {
    val uid: String get() = id

    // Totales calculados
    val totalHp: Int get() = level * (baseHp + npHp) + bonusHp
    val totalSp: Int get() = level * (baseSp + npSp) + bonusSp
    val totalAtk: Int get() = level * (baseAtk + npAtk) + bonusAtk
    val totalDef: Int get() = level * (baseDef + npDef) + bonusDef
    val totalInt: Int get() = level * (baseInt + npInt) + bonusInt
    val totalSpi: Int get() = level * (baseSpi + npSpi) + bonusSpi
    val totalSpd: Int get() = level * (baseSpd + npSpd) + bonusSpd
}