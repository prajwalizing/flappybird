package com.prajwalhs.flappybird.di

import com.prajwalhs.flappybird.data.repository.ScoreRepositoryImpl
import com.prajwalhs.flappybird.data.repository.SettingsRepositoryImpl
import com.prajwalhs.flappybird.domain.repository.ScoreRepository
import com.prajwalhs.flappybird.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindScoreRepository(impl: ScoreRepositoryImpl): ScoreRepository

    @Binds
    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
}