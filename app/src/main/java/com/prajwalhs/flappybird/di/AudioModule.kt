package com.prajwalhs.flappybird.di

import android.content.Context
import com.prajwalhs.flappybird.util.MusicManager
import com.prajwalhs.flappybird.util.SoundManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AudioModule {

    @Provides
    @Singleton
    fun provideSoundManager(@ApplicationContext context: Context): SoundManager =
        SoundManager(context)

    @Provides
    @Singleton
    fun provideMusicManager(@ApplicationContext context: Context): MusicManager =
        MusicManager(context)
}