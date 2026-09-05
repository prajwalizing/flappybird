package com.prajwalhs.flappybird.data.repository

import com.prajwalhs.flappybird.data.local.SettingsDataStore
import com.prajwalhs.flappybird.domain.model.Difficulty
import com.prajwalhs.flappybird.domain.model.Settings
import com.prajwalhs.flappybird.domain.model.SkyPalette
import com.prajwalhs.flappybird.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : SettingsRepository {
    override val settings: Flow<Settings> = settingsDataStore.settings

    override suspend fun updateSoundEnabled(enabled: Boolean) =
        settingsDataStore.updateSoundEnabled(enabled)

    override suspend fun updateMusicEnabled(enabled: Boolean) =
        settingsDataStore.updateMusicEnabled(enabled)

    override suspend fun updateDifficulty(difficulty: Difficulty) =
        settingsDataStore.updateDifficulty(difficulty)

    override suspend fun updateSky(sky: SkyPalette) =
        settingsDataStore.updateSky(sky)

    override suspend fun updateImmersiveModeEnabled(enabled: Boolean) =
        settingsDataStore.updateImmersiveModeEnabled(enabled)
}