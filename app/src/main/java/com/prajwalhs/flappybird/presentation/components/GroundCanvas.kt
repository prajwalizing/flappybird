package com.prajwalhs.flappybird.presentation.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.prajwalhs.flappybird.domain.model.GameConfig
import com.prajwalhs.flappybird.presentation.theme.GroundBrown

fun GroundCanvas(scope: DrawScope, screenHeight: Float, groundHeight: Float = GameConfig().groundHeight) {
    with(scope) {
        drawRect(
            color = GroundBrown,
            topLeft = Offset(0f, screenHeight - groundHeight),
            size = Size(size.width, groundHeight)
        )
    }
}