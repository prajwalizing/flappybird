package com.prajwalhs.flappybird.domain.usecase

import com.prajwalhs.flappybird.domain.model.Difficulty
import com.prajwalhs.flappybird.domain.model.SkyPalette
import com.prajwalhs.flappybird.domain.repository.SettingsRepository
import javax.inject.Inject

class SaveSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend fun setSoundEnabled(enabled: Boolean) = repository.updateSoundEnabled(enabled)
    suspend fun setMusicEnabled(enabled: Boolean) = repository.updateMusicEnabled(enabled)
    suspend fun setDifficulty(difficulty: Difficulty) = repository.updateDifficulty(difficulty)
    suspend fun setSky(sky: SkyPalette) = repository.updateSky(sky)
    suspend fun setImmersiveMode(enabled: Boolean) = repository.updateImmersiveModeEnabled(enabled)
}