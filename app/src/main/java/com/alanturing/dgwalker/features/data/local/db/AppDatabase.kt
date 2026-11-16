package com.alanturing.dgwalker.features.data.local.db

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.alanturing.dgwalker.features.data.local.db.dao.DigimonDao
import com.alanturing.dgwalker.features.data.local.db.dao.ItemDao
import com.alanturing.dgwalker.features.data.local.db.entities.LocalDigimonEntity
import com.alanturing.dgwalker.features.data.local.db.entities.LocalItemEntity

@Database(
    entities = [
        LocalDigimonEntity::class,
        LocalItemEntity::class
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun digimonDao(): DigimonDao
    abstract fun itemDao(): ItemDao

    companion object {
        const val DATABASE_NAME = "dgwalker_database.db"
    }
}