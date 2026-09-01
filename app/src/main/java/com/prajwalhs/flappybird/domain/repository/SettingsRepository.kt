package com.prajwalhs.flappybird.domain.repository

import com.prajwalhs.flappybird.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settings: Flow<Settings>
    suspend fun updateSoundEnabled(enabled: Boolean)
    suspend fun updateMusicEnabled(enabled: Boolean)
    suspend fun updateSkin(skin: String)
}