package com.prajwalhs.flappybird.presentation.components

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.prajwalhs.flappybird.domain.model.Pipe
import com.prajwalhs.flappybird.presentation.theme.PillarCapEnd
import com.prajwalhs.flappybird.presentation.theme.PillarCapMid1
import com.prajwalhs.flappybird.presentation.theme.PillarCapMid2
import com.prajwalhs.flappybird.presentation.theme.PillarCapStart
import com.prajwalhs.flappybird.presentation.theme.PillarEdgeShade
import com.prajwalhs.flappybird.presentation.theme.PillarShaftEnd
import com.prajwalhs.flappybird.presentation.theme.PillarShaftMid1
import com.prajwalhs.flappybird.presentation.theme.PillarShaftMid2
import com.prajwalhs.flappybird.presentation.theme.PillarShaftStart

private const val CAP_HEIGHT = 22f
private const val SHAFT_INSET_RATIO = 0.84f
private const val SHAFT_CORNER = 4f
private const val CAP_CORNER = 7f

fun PipeCanvas(scope: DrawScope, pipe: Pipe) {
    with(scope) {
        val capWidth = pipe.width
        val shaftWidth = pipe.width * SHAFT_INSET_RATIO
        val shaftLeft = pipe.x + (capWidth - shaftWidth) / 2f
        val capLeft = pipe.x

        val shaftBrush = Brush.horizontalGradient(
            0f to PillarShaftStart,
            0.26f to PillarShaftMid1,
            0.62f to PillarShaftMid2,
            1f to PillarShaftEnd,
            startX = shaftLeft,
            endX = shaftLeft + shaftWidth
        )
        val capBrush = Brush.horizontalGradient(
            0f to PillarCapStart,
            0.24f to PillarCapMid1,
            0.6f to PillarCapMid2,
            1f to PillarCapEnd,
            startX = capLeft,
            endX = capLeft + capWidth
        )

        // Top pillar: shaft from the ceiling down to just above the gap, capped at the gap edge.
        val topShaftHeight = pipe.gapTopY - CAP_HEIGHT
        if (topShaftHeight > 0f) {
            drawRoundRect(
                brush = shaftBrush,
                topLeft = Offset(shaftLeft, 0f),
                size = Size(shaftWidth, topShaftHeight),
                cornerRadius = CornerRadius(SHAFT_CORNER, SHAFT_CORNER)
            )
            drawEdgeShading(shaftLeft, 0f, shaftWidth, topShaftHeight)
        }
        drawRoundRect(
            brush = capBrush,
            topLeft = Offset(capLeft, pipe.gapTopY - CAP_HEIGHT),
            size = Size(capWidth, CAP_HEIGHT),
            cornerRadius = CornerRadius(CAP_CORNER, CAP_CORNER)
        )
        drawRect(
            color = PillarEdgeShade.copy(alpha = 0.22f),
            topLeft = Offset(capLeft + 4f, pipe.gapTopY - 3f),
            size = Size(capWidth - 8f, 3f)
        )

        // Bottom pillar: capped at the gap edge, shaft continues down to the ground.
        drawRoundRect(
            brush = capBrush,
            topLeft = Offset(capLeft, pipe.gapBottomY),
            size = Size(capWidth, CAP_HEIGHT),
            cornerRadius = CornerRadius(CAP_CORNER, CAP_CORNER)
        )
        drawRect(
            color = PillarEdgeShade.copy(alpha = 0.22f),
            topLeft = Offset(capLeft + 4f, pipe.gapBottomY),
            size = Size(capWidth - 8f, 3f)
        )
        val bottomShaftTop = pipe.gapBottomY + CAP_HEIGHT
        val bottomShaftHeight = size.height - bottomShaftTop
        if (bottomShaftHeight > 0f) {
            drawRoundRect(
                brush = shaftBrush,
                topLeft = Offset(shaftLeft, bottomShaftTop),
                size = Size(shaftWidth, bottomShaftHeight),
                cornerRadius = CornerRadius(SHAFT_CORNER, SHAFT_CORNER)
            )
            drawEdgeShading(shaftLeft, bottomShaftTop, shaftWidth, bottomShaftHeight)
        }
    }
}

private fun DrawScope.drawEdgeShading(x: Float, y: Float, width: Float, height: Float) {
    val shade = PillarEdgeShade.copy(alpha = 0.18f)
    drawRect(color = shade, topLeft = Offset(x, y), size = Size(3f, height))
    drawRect(color = shade, topLeft = Offset(x + width - 3f, y), size = Size(3f, height))
}
