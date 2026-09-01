package com.prajwalhs.flappybird.domain.model

data class Pipe(
    val x: Float,
    val gapCenterY: Float,
    val gapHeight: Float,
    val width: Float,
    val passed: Boolean = false
) {
    val gapTopY: Float get() = gapCenterY - gapHeight / 2f
    val gapBottomY: Float get() = gapCenterY + gapHeight / 2f
}