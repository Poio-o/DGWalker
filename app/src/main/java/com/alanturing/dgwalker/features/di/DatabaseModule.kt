package com.alanturing.dgwalker.features.di

import android.content.Context
import androidx.room3.Room
import com.alanturing.dgwalker.features.data.local.db.AppDatabase
import com.alanturing.dgwalker.features.data.local.db.dao.DigimonDao
import com.alanturing.dgwalker.features.data.local.db.dao.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDigimonDao(database: AppDatabase): DigimonDao = database.digimonDao()

    @Provides
    @Singleton
    fun provideItemDao(database: AppDatabase): ItemDao = database.itemDao()
}