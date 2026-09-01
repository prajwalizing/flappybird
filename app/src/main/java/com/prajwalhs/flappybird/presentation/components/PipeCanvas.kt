package com.prajwalhs.flappybird.presentation.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.prajwalhs.flappybird.domain.model.Pipe
import com.prajwalhs.flappybird.presentation.theme.PipeGreen

fun PipeCanvas(scope: DrawScope, pipe: Pipe) {
    with(scope) {
        // Top pipe
        drawRect(
            color = PipeGreen,
            topLeft = Offset(pipe.x, 0f),
            size = Size(pipe.width, pipe.gapTopY)
        )
        // Bottom pipe
        drawRect(
            color = PipeGreen,
            topLeft = Offset(pipe.x, pipe.gapBottomY),
            size = Size(pipe.width, size.height - pipe.gapBottomY)
        )
    }
}