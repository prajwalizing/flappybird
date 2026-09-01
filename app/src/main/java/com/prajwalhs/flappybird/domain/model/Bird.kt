package com.prajwalhs.flappybird.domain.model

data class Bird(
    val x: Float = 150f,
    val y: Float = 400f,
    val velocityY: Float = 0f,
    val rotationDegrees: Float = 0f,
    val radius: Float = 30f
)