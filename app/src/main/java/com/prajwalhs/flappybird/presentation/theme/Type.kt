package com.prajwalhs.flappybird.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/** Wordmark / hero display type — e.g. the "FLAPP" title on the menu. */
val DisplayStyle = TextStyle(
    fontFamily = OutfitFontFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 72.sp,
    letterSpacing = (-0.04).em
)

/** In-game HUD score number. */
val ScoreStyle = TextStyle(
    fontFamily = OutfitFontFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 68.sp,
    letterSpacing = (-0.04).em
)

/** Card/section titles — "Nice run", "Take a breath", "Settings". */
val TitleStyle = TextStyle(
    fontFamily = OutfitFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 30.sp,
    letterSpacing = (-0.02).em
)

/** Body copy and button labels. */
val BodyStyle = TextStyle(
    fontFamily = OutfitFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp
)

/** Uppercase mono labels — stat names, HUD tags. Apply letterSpacing per-use (0.14–0.34em). */
val LabelStyle = TextStyle(
    fontFamily = IBMPlexMonoFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    letterSpacing = 0.18.em
)

val AppTypography = Typography(
    headlineLarge = DisplayStyle,
    titleLarge = TitleStyle,
    bodyLarge = BodyStyle,
    labelSmall = LabelStyle
)
