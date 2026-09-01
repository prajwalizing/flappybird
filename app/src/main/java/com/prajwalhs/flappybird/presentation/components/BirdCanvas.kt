package com.prajwalhs.flappybird.presentation.components

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import com.prajwalhs.flappybird.domain.model.Bird
import com.prajwalhs.flappybird.presentation.theme.BirdYellow

fun BirdCanvas(scope: DrawScope, bird: Bird) {
    with(scope) {
        rotate(degrees = bird.rotationDegrees, pivot = androidx.compose.ui.geometry.Offset(bird.x, bird.y)) {
            drawCircle(
                color = BirdYellow,
                radius = bird.radius,
                center = androidx.compose.ui.geometry.Offset(bird.x, bird.y)
            )
        }
    }
}