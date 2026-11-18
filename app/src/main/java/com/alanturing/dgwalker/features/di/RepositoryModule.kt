package com.alanturing.dgwalker.features.di

import com.alanturing.dgwalker.features.data.repository.DigimonRepositoryImpl
import com.alanturing.dgwalker.features.data.repository.InventoryRepositoryImpl
import com.alanturing.dgwalker.features.domain.repository.DigimonRepository
import com.alanturing.dgwalker.features.domain.repository.InventoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDigimonRepository(impl: DigimonRepositoryImpl): DigimonRepository

    @Binds
    @Singleton
    abstract fun bindInventoryRepository(impl: InventoryRepositoryImpl): InventoryRepository
}