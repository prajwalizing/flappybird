package com.prajwalhs.flappybird.domain.usecase

import com.prajwalhs.flappybird.domain.model.Settings
import com.prajwalhs.flappybird.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<Settings> = repository.settings
}