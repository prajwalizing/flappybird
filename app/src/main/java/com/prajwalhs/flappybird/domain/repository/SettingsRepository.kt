package com.prajwalhs.flappybird.domain.repository

import com.prajwalhs.flappybird.domain.model.Difficulty
import com.prajwalhs.flappybird.domain.model.Settings
import com.prajwalhs.flappybird.domain.model.SkyPalette
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settings: Flow<Settings>
    suspend fun updateSoundEnabled(enabled: Boolean)
    suspend fun updateMusicEnabled(enabled: Boolean)
    suspend fun updateDifficulty(difficulty: Difficulty)
    suspend fun updateSky(sky: SkyPalette)
    suspend fun updateImmersiveModeEnabled(enabled: Boolean)
}