package com.prajwalhs.flappybird.presentation.components

import androidx.compose.ui.graphics.drawscope.DrawScope
import com.prajwalhs.flappybird.presentation.theme.SkyBlue

fun BackgroundCanvas(scope: DrawScope) {
    with(scope) {
        drawRect(color = SkyBlue)
    }
}