package com.prajwalhs.flappybird.presentation.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.prajwalhs.flappybird.domain.model.GameConfig
import com.prajwalhs.flappybird.presentation.theme.PaletteColors
import kotlin.math.cos
import kotlin.math.sin

fun BackgroundCanvas(
    scope: DrawScope,
    palette: PaletteColors,
    groundHeight: Float = GameConfig().groundHeight
) {
    with(scope) {
        val w = size.width
        val h = size.height
        val groundTop = h - groundHeight

        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(palette.top, palette.bottom),
                startY = 0f,
                endY = h
            )
        )

        for (i in 0 until 4) {
            val cx = i * 137f + 40f
            val cy = 120f + i * 96f
            drawOval(color = palette.cloud, topLeft = Offset(cx - 62f, cy - 20f), size = Size(124f, 40f))
            drawOval(color = palette.cloud, topLeft = Offset(cx - 6f, cy - 28f), size = Size(80f, 32f))
        }

        val hillFar = Path().apply {
            moveTo(0f, groundTop)
            for (i in 0..6) {
                val x = (i / 6f) * w
                val y = groundTop - 60f - sin(i * 1.5f) * 26f
                lineTo(x, y)
            }
            lineTo(w, groundTop)
            close()
        }
        drawPath(hillFar, color = palette.hill)

        val hillNear = Path().apply {
            moveTo(0f, groundTop)
            for (i in 0..5) {
                val x = (i / 5f) * w
                val y = groundTop - 26f - cos(i * 1.9f) * 16f
                lineTo(x, y)
            }
            lineTo(w, groundTop)
            close()
        }
        drawPath(hillNear, color = palette.hill2)
    }
}
