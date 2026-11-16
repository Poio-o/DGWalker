package com.alanturing.dgwalker.features.data.local.db.entities

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "local_inventory",
    foreignKeys = [
        ForeignKey(
            entity = LocalDigimonEntity::class,
            parentColumns = ["digimonId"],
            childColumns = ["assignedDigimonId"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["assignedDigimonId"])]
)
data class LocalItemEntity(
    @PrimaryKey
    val itemId: String,
    val name: String,
    val category: String,
    val quantity: Int,
    val iconUrl: String,
    val description: String,
    val hungerRestore: Int = 0,
    val friendshipGain: Int = 0,
    val assignedDigimonId: String? = null // FK: Digimon al que está asignado (puede ser nulo)
)