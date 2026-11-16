package com.alanturing.dgwalker.features.data.local.db.relations

import androidx.room3.Embedded
import androidx.room3.Relation
import com.alanturing.dgwalker.features.data.local.db.entities.LocalDigimonEntity
import com.alanturing.dgwalker.features.data.local.db.entities.LocalItemEntity

data class DigimonWithItems(
    @Embedded
    val digimon: LocalDigimonEntity,

    @Relation(
        parentColumns = ["digimonId"],
        entityColumns = ["assignedDigimonId"]
    )
    val assignedItems: List<LocalItemEntity>
)