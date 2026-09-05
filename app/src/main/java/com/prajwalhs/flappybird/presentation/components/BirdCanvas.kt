package com.prajwalhs.flappybird.presentation.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import com.prajwalhs.flappybird.domain.model.Bird
import com.prajwalhs.flappybird.presentation.theme.BallDark
import com.prajwalhs.flappybird.presentation.theme.BallHighlight
import com.prajwalhs.flappybird.presentation.theme.BallInnerShade
import com.prajwalhs.flappybird.presentation.theme.BallMid
import com.prajwalhs.flappybird.presentation.theme.BallShadow
import com.prajwalhs.flappybird.presentation.theme.HudShadow

fun BirdCanvas(scope: DrawScope, bird: Bird) {
    with(scope) {
        val center = Offset(bird.x, bird.y)

        // Drop shadow, cast slightly below the ball regardless of tilt.
        drawOval(
            color = HudShadow.copy(alpha = 0.18f),
            topLeft = Offset(center.x - bird.radius * 0.95f + 2f, center.y + bird.radius * 0.55f),
            size = Size(bird.radius * 1.9f, bird.radius * 1.1f)
        )

        rotate(degrees = bird.rotationDegrees, pivot = center) {
            val ballBrush = Brush.radialGradient(
                0f to BallHighlight,
                0.38f to BallMid,
                0.78f to BallDark,
                1f to BallShadow,
                center = Offset(center.x - bird.radius * 0.36f, center.y - bird.radius * 0.44f),
                radius = bird.radius * 1.25f
            )
            drawCircle(brush = ballBrush, radius = bird.radius, center = center)

            // Inner shade ring to sell the sphere, approximating the design's inset shadow.
            drawCircle(
                color = BallInnerShade.copy(alpha = 0.22f),
                radius = bird.radius - bird.radius * 0.15f,
                center = center,
                style = Stroke(width = bird.radius * 0.3f)
            )

            drawOval(
                color = Color.White.copy(alpha = 0.75f),
                topLeft = Offset(center.x - bird.radius * 0.55f, center.y - bird.radius * 0.65f),
                size = Size(bird.radius * 0.6f, bird.radius * 0.4f)
            )
        }
    }
}
