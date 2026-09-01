package com.prajwalhs.flappybird.presentation.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.hilt.navigation.compose.hiltViewModel
import com.prajwalhs.flappybird.domain.model.GameState
import com.prajwalhs.flappybird.presentation.components.BackgroundCanvas
import com.prajwalhs.flappybird.presentation.components.BirdCanvas
import com.prajwalhs.flappybird.presentation.components.GameOverOverlay
import com.prajwalhs.flappybird.presentation.components.GroundCanvas
import com.prajwalhs.flappybird.presentation.components.PipeCanvas
import com.prajwalhs.flappybird.presentation.components.ScoreText
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun GameScreen(
    onBackToMenu: () -> Unit,
    viewModel: GameViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        // fires immediately when finger touches down
                        isPressed = true
                        viewModel.onTap()
                        try {
                            awaitRelease() // suspends until finger lifts
                        } finally {
                            isPressed = false
                        }
                    }
                )
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            if (uiState.screenWidth == 0f) {
                viewModel.setScreenSize(size.width, size.height)
            }

            BackgroundCanvas(this)
            uiState.pipes.forEach { pipe -> PipeCanvas(this, pipe) }
            GroundCanvas(this, uiState.screenHeight)
            BirdCanvas(this, uiState.bird)
        }

        ScoreText(score = uiState.score, highScore = uiState.highScore)

        val gameOverState = uiState.gameState
        if (gameOverState is GameState.GameOver) {
            GameOverOverlay(
                score = gameOverState.score,
                highScore = gameOverState.highScore,
                isNewHighScore = gameOverState.isNewHighScore,
                onRestart = { viewModel.onTap() },
                onMenu = onBackToMenu
            )
        }
    }

    // Repeated flap while finger is held down (only matters when actively Playing)
    LaunchedEffect(isPressed) {
        if (isPressed) {
            while (isPressed) {
                delay(150.milliseconds) // interval between auto-flaps while held — tune this for feel
                if (uiState.gameState is GameState.Playing) {
                    viewModel.onTap()
                }
            }
        }
    }

    // Game loop: runs while this screen is composed, drives physics via withFrameNanos
    LaunchedEffect(Unit) {
        var lastFrameTimeNanos = 0L
        while (true) {
            withFrameNanos { frameTimeNanos ->
                if (lastFrameTimeNanos != 0L) {
                    val deltaSeconds = (frameTimeNanos - lastFrameTimeNanos) / 1_000_000_000f
                    viewModel.onFrame(deltaSeconds)
                }
                lastFrameTimeNanos = frameTimeNanos
            }
        }
    }
}