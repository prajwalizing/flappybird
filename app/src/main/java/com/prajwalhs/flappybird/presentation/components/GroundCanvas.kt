package com.prajwalhs.flappybird.presentation.components

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.prajwalhs.flappybird.domain.model.GameConfig
import com.prajwalhs.flappybird.presentation.theme.PaletteColors

fun GroundCanvas(
    scope: DrawScope,
    screenHeight: Float,
    palette: PaletteColors,
    groundHeight: Float = GameConfig().groundHeight
) {
    with(scope) {
        val top = screenHeight - groundHeight
        drawRect(color = palette.ground, topLeft = Offset(0f, top), size = Size(size.width, groundHeight))
        drawRect(color = palette.groundEdge, topLeft = Offset(0f, top), size = Size(size.width, 5f))

        var x = 6f
        while (x < size.width) {
            drawRoundRect(
                color = palette.groundDark,
                topLeft = Offset(x, top + 18f),
                size = Size(26f, 8f),
                cornerRadius = CornerRadius(4f, 4f)
            )
            x += 44f
        }
    }
}
