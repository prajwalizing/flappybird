package com.prajwalhs.flappybird.domain.model

data class Settings(
    val soundEnabled: Boolean = true,
    val musicEnabled: Boolean = true,
    val selectedSkin: String = "default"
)