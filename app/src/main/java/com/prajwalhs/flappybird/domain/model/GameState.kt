package com.prajwalhs.flappybird.domain.model

sealed class GameState {
    data object Ready : GameState()
    data object Playing : GameState()
    data object Paused : GameState()
    data class GameOver(val score: Int, val highScore: Int, val isNewHighScore: Boolean) : GameState()
}