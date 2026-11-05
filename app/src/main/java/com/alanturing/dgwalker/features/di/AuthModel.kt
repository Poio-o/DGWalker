package com.alanturing.dgwalker.features.di

import com.alanturing.dgwalker.features.data.local.LocalAuthServiceImpl
import com.alanturing.dgwalker.features.domain.repository.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModel{

    @Provides
    @Singleton
    fun provideAuthService(): AuthService {
        return LocalAuthServiceImpl()
    }
}