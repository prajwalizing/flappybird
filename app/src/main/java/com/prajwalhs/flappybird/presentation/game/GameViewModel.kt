package com.prajwalhs.flappybird.presentation.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prajwalhs.flappybird.domain.model.Bird
import com.prajwalhs.flappybird.domain.model.GameConfig
import com.prajwalhs.flappybird.domain.model.GameState
import com.prajwalhs.flappybird.domain.usecase.CalculateScoreUseCase
import com.prajwalhs.flappybird.domain.usecase.CheckCollisionUseCase
import com.prajwalhs.flappybird.domain.usecase.GetHighScoreUseCase
import com.prajwalhs.flappybird.domain.usecase.MovePipesUseCase
import com.prajwalhs.flappybird.domain.usecase.SaveHighScoreUseCase
import com.prajwalhs.flappybird.domain.usecase.UpdateBirdPhysicsUseCase
import com.prajwalhs.flappybird.util.SoundManager
import com.prajwalhs.flappybird.util.VibrationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val updateBirdPhysicsUseCase: UpdateBirdPhysicsUseCase,
    private val movePipesUseCase: MovePipesUseCase,
    private val checkCollisionUseCase: CheckCollisionUseCase,
    private val calculateScoreUseCase: CalculateScoreUseCase,
    private val getHighScoreUseCase: GetHighScoreUseCase,
    private val saveHighScoreUseCase: SaveHighScoreUseCase,
    private val soundManager: SoundManager,
    private val vibrationHelper: VibrationHelper
) : ViewModel() {

    private val config = GameConfig()

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState

    init {
        viewModelScope.launch {
            getHighScoreUseCase().collect { high ->
                _uiState.update { it.copy(highScore = high) }
            }
        }
    }

    fun setScreenSize(width: Float, height: Float) {
        _uiState.update {
            if (it.screenWidth == 0f) {
                it.copy(
                    screenWidth = width,
                    screenHeight = height,
                    bird = Bird(radius = config.birdRadius, y = height / 2f)
                )
            } else it
        }
    }

    fun onTap() {
        val state = _uiState.value
        when (state.gameState) {
            is GameState.Ready -> {
                _uiState.update { it.copy(gameState = GameState.Playing) }
                flap()
            }
            is GameState.Playing -> flap()
            is GameState.GameOver -> resetGame()
        }
    }

    private fun flap() {
        soundManager.playFlap()
        _uiState.update {
            it.copy(bird = updateBirdPhysicsUseCase.applyFlap(it.bird, config))
        }
    }

    /** Called every frame from GameScreen's game loop while state is Playing. */
    fun onFrame(deltaTimeSeconds: Float) {
        val current = _uiState.value
        if (current.gameState !is GameState.Playing) return
        if (current.screenWidth == 0f) return

        val cappedDelta = deltaTimeSeconds.coerceAtMost(0.033f) // avoid big jumps after frame drops

        val updatedBird = updateBirdPhysicsUseCase(current.bird, config, cappedDelta)

        val movedPipes = movePipesUseCase(
            pipes = current.pipes,
            config = config,
            deltaTimeSeconds = cappedDelta,
            screenWidth = current.screenWidth,
            screenHeight = current.screenHeight
        )

        val (scoredPipes, scoreDelta) = calculateScoreUseCase(updatedBird, movedPipes)
        if (scoreDelta > 0) soundManager.playScore()

        val collided = checkCollisionUseCase(updatedBird, scoredPipes, config, current.screenHeight)

        if (collided) {
            soundManager.playHit()
            vibrationHelper.vibrateCollision()
            val finalScore = current.score + scoreDelta
            viewModelScope.launch {
                val isNewHigh = saveHighScoreUseCase(finalScore)
                _uiState.update {
                    it.copy(
                        bird = updatedBird,
                        pipes = scoredPipes,
                        score = finalScore,
                        gameState = GameState.GameOver(
                            score = finalScore,
                            highScore = maxOf(finalScore, it.highScore),
                            isNewHighScore = isNewHigh
                        )
                    )
                }
            }
        } else {
            _uiState.update {
                it.copy(
                    bird = updatedBird,
                    pipes = scoredPipes,
                    score = it.score + scoreDelta
                )
            }
        }
    }

    private fun resetGame() {
        val state = _uiState.value
        _uiState.update {
            GameUiState(
                bird = Bird(radius = config.birdRadius, y = state.screenHeight / 2f),
                pipes = emptyList(),
                score = 0,
                highScore = state.highScore,
                gameState = GameState.Ready,
                screenWidth = state.screenWidth,
                screenHeight = state.screenHeight
            )
        }
    }
}