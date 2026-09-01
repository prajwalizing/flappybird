package com.prajwalhs.flappybird.domain.usecase

import com.prajwalhs.flappybird.domain.repository.SettingsRepository
import javax.inject.Inject

class SaveSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend fun setSoundEnabled(enabled: Boolean) = repository.updateSoundEnabled(enabled)
    suspend fun setMusicEnabled(enabled: Boolean) = repository.updateMusicEnabled(enabled)
    suspend fun setSkin(skin: String) = repository.updateSkin(skin)
}