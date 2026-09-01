package com.prajwalhs.flappybird.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

// Qualifiers so Hilt can tell the two DataStore<Preferences> instances apart
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ScorePrefs

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SettingsPrefs

private val Context.scoreDataStoreFile by preferencesDataStore(name = "score_prefs")
private val Context.settingsDataStoreFile by preferencesDataStore(name = "settings_prefs")

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    @ScorePrefs
    fun provideScoreDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.scoreDataStoreFile

    @Provides
    @Singleton
    @SettingsPrefs
    fun provideSettingsDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.settingsDataStoreFile
}