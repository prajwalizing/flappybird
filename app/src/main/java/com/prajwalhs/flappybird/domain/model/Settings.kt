package com.prajwalhs.flappybird.domain.model

data class Settings(
    val soundEnabled: Boolean = true,
    val musicEnabled: Boolean = true,
    val difficulty: Difficulty = Difficulty.CLASSIC,
    val sky: SkyPalette = SkyPalette.DAY,
    val immersiveModeEnabled: Boolean = true
)