package com.alanturing.dgwalker.features.di

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.SQLiteConnection
import com.alanturing.dgwalker.features.data.local.db.AppDatabase
import com.alanturing.dgwalker.features.data.local.db.dao.DigimonDao
import com.alanturing.dgwalker.features.data.local.db.dao.ItemDao
import com.alanturing.dgwalker.features.data.local.db.entities.LocalDigimonEntity
import com.alanturing.dgwalker.features.data.local.db.entities.LocalItemEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        databaseProvider: Provider<AppDatabase>
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override suspend fun onCreate(connection: SQLiteConnection) {
                    super.onCreate(connection)
                    CoroutineScope(Dispatchers.IO).launch {
                        val database = databaseProvider.get()
                        seedDatabase(database.digimonDao(), database.itemDao())
                    }
                }
            })
            .build()
    }

    private suspend fun seedDatabase(digimonDao: DigimonDao, itemDao: ItemDao) {
        val initialDigimons = listOf(
            LocalDigimonEntity(
                digimonId = "digi_1",
                slotNumber = 1,
                nickname = "Tsunemon",
                speciesName = "Tsunemon",
                generation = "In-Training",
                attribute = "Data",
                type = "Lesser",
                spriteUrl = "https://digimon.net/attic/uploads/2021/01/Tsunemon.png",
                level = 5,
                currentExp = 50,
                friendship = 60,
                hunger = 40,
                fatigue = 10,
                baseHp = 20, baseSp = 10, baseAtk = 8, baseDef = 7, baseInt = 6, baseSpi = 6, baseSpd = 9,
                stepsWalked = 67,
                isWalking = true
            ),
            LocalDigimonEntity(
                digimonId = "digi_2",
                slotNumber = 2,
                nickname = "Gekkomon",
                speciesName = "Gekkomon",
                generation = "Champion",
                attribute = "Virus",
                type = "Amphibian",
                spriteUrl = "https://digimon.net/attic/uploads/2021/01/Gekkomon.png",
                level = 15,
                currentExp = 200,
                friendship = 75,
                hunger = 30,
                fatigue = 5,
                baseHp = 50, baseSp = 30, baseAtk = 25, baseDef = 20, baseInt = 18, baseSpi = 18, baseSpd = 22,
                stepsWalked = 150,
                isWalking = false
            ),
            LocalDigimonEntity(
                digimonId = "digi_3",
                slotNumber = 3,
                nickname = "Betamon",
                speciesName = "Betamon",
                generation = "Rookie",
                attribute = "Virus",
                type = "Amphibian",
                spriteUrl = "https://digimon.net/attic/uploads/2021/01/Betamon.png",
                level = 10,
                currentExp = 120,
                friendship = 50,
                hunger = 50,
                fatigue = 15,
                baseHp = 35, baseSp = 20, baseAtk = 18, baseDef = 15, baseInt = 14, baseSpi = 14, baseSpd = 16,
                stepsWalked = 80,
                isWalking = false
            ),
            LocalDigimonEntity(
                digimonId = "digi_4",
                slotNumber = 4,
                nickname = "BeelStarmon",
                speciesName = "BeelStarmon",
                generation = "Mega",
                attribute = "Demon Lord",
                type = "Demon Man",
                spriteUrl = "https://digimon.net/attic/uploads/2021/01/BeelStarmon.png",
                level = 45,
                currentExp = 800,
                friendship = 90,
                hunger = 20,
                fatigue = 0,
                baseHp = 120, baseSp = 80, baseAtk = 95, baseDef = 70, baseInt = 85, baseSpi = 80, baseSpd = 90,
                stepsWalked = 500,
                isWalking = false
            )
        )

        val initialItems = listOf(
            LocalItemEntity(
                itemId = "item_1",
                name = "Manzana Digimon",
                category = "Food",
                quantity = 5,
                iconUrl = "https://digimon.net/attic/uploads/2021/01/item_apple.png",
                description = "Restaura 25 de hambre y aumenta la amistad",
                hungerRestore = 25,
                friendshipGain = 10
            ),
            LocalItemEntity(
                itemId = "item_2",
                name = "Carne Digimon",
                category = "Food",
                quantity = 3,
                iconUrl = "https://digimon.net/attic/uploads/2021/01/item_meat.png",
                description = "Restaura 45 de hambre y aumenta bastante la amistad",
                hungerRestore = 45,
                friendshipGain = 20
            )
        )

        digimonDao.insertTeam(initialDigimons)
        itemDao.insertItems(initialItems)
    }

    @Provides
    @Singleton
    fun provideDigimonDao(database: AppDatabase): DigimonDao = database.digimonDao()

    @Provides
    @Singleton
    fun provideItemDao(database: AppDatabase): ItemDao = database.itemDao()
}
