package com.prajwalhs.flappybird.presentation.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prajwalhs.flappybird.domain.model.Difficulty
import com.prajwalhs.flappybird.domain.model.Settings
import com.prajwalhs.flappybird.domain.model.SkyPalette
import com.prajwalhs.flappybird.domain.usecase.GetSettingsUseCase
import com.prajwalhs.flappybird.domain.usecase.ResetHighScoreUseCase
import com.prajwalhs.flappybird.domain.usecase.SaveSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val saveSettingsUseCase: SaveSettingsUseCase,
    private val resetHighScoreUseCase: ResetHighScoreUseCase
) : ViewModel() {

    private val _settings = MutableStateFlow(Settings())
    val settings: StateFlow<Settings> = _settings

    init {
        viewModelScope.launch {
            getSettingsUseCase().collect { _settings.value = it }
        }
    }

    fun toggleSound(enabled: Boolean) {
        viewModelScope.launch { saveSettingsUseCase.setSoundEnabled(enabled) }
    }

    fun toggleMusic(enabled: Boolean) {
        viewModelScope.launch { saveSettingsUseCase.setMusicEnabled(enabled) }
    }

    fun setDifficulty(difficulty: Difficulty) {
        viewModelScope.launch { saveSettingsUseCase.setDifficulty(difficulty) }
    }

    fun setSky(sky: SkyPalette) {
        viewModelScope.launch { saveSettingsUseCase.setSky(sky) }
    }

    fun toggleImmersiveMode(enabled: Boolean) {
        viewModelScope.launch { saveSettingsUseCase.setImmersiveMode(enabled) }
    }

    fun resetBestScore() {
        viewModelScope.launch { resetHighScoreUseCase() }
    }
}
