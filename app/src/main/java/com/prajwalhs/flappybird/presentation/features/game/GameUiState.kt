package com.prajwalhs.flappybird.presentation.features.game

import com.prajwalhs.flappybird.domain.model.Bird
import com.prajwalhs.flappybird.domain.model.GameState
import com.prajwalhs.flappybird.domain.model.Pipe
import com.prajwalhs.flappybird.domain.model.SkyPalette

data class GameUiState(
    val bird: Bird = Bird(),
    val pipes: List<Pipe> = emptyList(),
    val score: Int = 0,
    val highScore: Int = 0,
    val gameState: GameState = GameState.Ready,
    val screenWidth: Float = 0f,
    val screenHeight: Float = 0f,
    val sky: SkyPalette = SkyPalette.DAY,
    val immersiveModeEnabled: Boolean = true
)
