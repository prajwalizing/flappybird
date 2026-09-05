package com.prajwalhs.flappybird.presentation.features.game

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.prajwalhs.flappybird.domain.model.GameState
import com.prajwalhs.flappybird.presentation.components.BackgroundCanvas
import com.prajwalhs.flappybird.presentation.components.BirdCanvas
import com.prajwalhs.flappybird.presentation.components.GameOverOverlay
import com.prajwalhs.flappybird.presentation.components.GroundCanvas
import com.prajwalhs.flappybird.presentation.components.PauseOverlay
import com.prajwalhs.flappybird.presentation.components.PipeCanvas
import com.prajwalhs.flappybird.presentation.components.ScoreText
import com.prajwalhs.flappybird.presentation.theme.paletteFor

@Composable
fun GameScreen(
    onBackToMenu: () -> Unit,
    viewModel: GameViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Hide system nav/status bars while the game screen is on-screen (if enabled), restore on exit.
    val view = LocalView.current
    val immersiveModeEnabled = uiState.immersiveModeEnabled
    DisposableEffect(immersiveModeEnabled) {
        val window = view.context.findActivity()?.window
        val insetsController = window?.let { WindowInsetsControllerCompat(it, view) }
        if (immersiveModeEnabled) {
            if (window != null) WindowCompat.setDecorFitsSystemWindows(window, false)
            insetsController?.apply {
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                hide(WindowInsetsCompat.Type.systemBars())
            }
        }
        onDispose {
            if (window != null) WindowCompat.setDecorFitsSystemWindows(window, true)
            insetsController?.show(WindowInsetsCompat.Type.systemBars())
        }
    }

    // Tap-to-flap only while the run is live — Paused/GameOver expose their own
    // buttons and must not have the background gesture stealing those taps.
    val tapEnabled = uiState.gameState is GameState.Ready || uiState.gameState is GameState.Playing

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(tapEnabled) {
                if (!tapEnabled) return@pointerInput
                detectTapGestures(
                    onPress = {
                        viewModel.onTap()
                        viewModel.setRising(true)
                        try {
                            awaitRelease()
                        } finally {
                            viewModel.setRising(false)
                        }
                    }
                )
            }
    ) {
        val palette = paletteFor(uiState.sky)

        Canvas(modifier = Modifier.fillMaxSize()) {
            if (uiState.screenWidth == 0f) {
                viewModel.setScreenSize(size.width, size.height)
            }

            BackgroundCanvas(this, palette)
            uiState.pipes.forEach { pipe -> PipeCanvas(this, pipe) }
            GroundCanvas(this, uiState.screenHeight, palette)
            BirdCanvas(this, uiState.bird)
        }

        val gameState = uiState.gameState
        if (gameState !is GameState.GameOver) {
            ScoreText(
                score = uiState.score,
                highScore = uiState.highScore,
                onBack = onBackToMenu,
                onPause = { viewModel.pause() }
            )
        }

        if (gameState is GameState.Paused) {
            PauseOverlay(
                score = uiState.score,
                highScore = uiState.highScore,
                onResume = { viewModel.resume() },
                onRestart = { viewModel.restart() },
                onMenu = onBackToMenu
            )
        }

        if (gameState is GameState.GameOver) {
            GameOverOverlay(
                score = gameState.score,
                highScore = gameState.highScore,
                isNewHighScore = gameState.isNewHighScore,
                onRestart = { viewModel.restart() },
                onMenu = onBackToMenu
            )
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

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
