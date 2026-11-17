package com.alanturing.dgwalker.features.data.local.db.entities

import androidx.room3.PrimaryKey
import androidx.room3.Entity

@Entity(tableName = "walking_team")
data class LocalDigimonEntity(
    @PrimaryKey
    val digimonId: String,
    val slotNumber: Int,
    val nickname: String,
    val speciesName: String,
    val generation: String,
    val attribute: String,
    val type: String,
    val spriteUrl: String,

    val level: Int,
    val currentExp: Int,
    val friendship: Int,        // 0 (no amigo) a 100 (amigo)
    val hunger: Int,          // 0 (saciado) a 100 (hambre)
    val fatigue: Int,           // 0 (descansado) a 100 (cansado)

    // Base
    val baseHp: Int,
    val baseSp: Int,
    val baseAtk: Int,
    val baseDef: Int,
    val baseInt: Int,
    val baseSpi: Int,
    val baseSpd: Int,

    // Natural Power 1 a 30
    val npHp: Int = 10,
    val npSp: Int = 10,
    val npAtk: Int = 10,
    val npDef: Int = 10,
    val npInt: Int = 10,
    val npSpi: Int = 10,
    val npSpd: Int = 10,

    // Bonificaciones de entrenamiento 0 a 500
    val bonusHp: Int = 0,
    val bonusSp: Int = 0,
    val bonusAtk: Int = 0,
    val bonusDef: Int = 0,
    val bonusInt: Int = 0,
    val bonusSpi: Int = 0,
    val bonusSpd: Int = 0,

    // Datos del paseo actual
    val stepsWalked: Int = 0,
    val expGained: Int = 0,
    val friendshipGained: Int = 0,
    val isWalking: Boolean = true
) {
    // Alias para navegación
    val uid: String get() = digimonId
}