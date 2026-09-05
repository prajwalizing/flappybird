package com.prajwalhs.flappybird.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = GoldBottom,
    secondary = InkPrimary,
    onPrimary = InkOnGold,
    surface = SurfaceCard,
    background = SurfaceSheet,
    onSurface = InkPrimary,
    onBackground = InkPrimary
)

@Composable
fun FlappyBirdTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        content = content
    )
}