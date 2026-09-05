package com.prajwalhs.flappybird.presentation.theme

import androidx.compose.ui.graphics.Color
import com.prajwalhs.flappybird.domain.model.SkyPalette

// ── Ink / neutral text ──────────────────────────────────────────
val InkPrimary = Color(0xFF12333D)
val InkSecondary = Color(0xFF4A6870)
val InkLabel = Color(0xFF7C959C)
val InkMuted = Color(0xFF8A9EA3)

// ── Surfaces ─────────────────────────────────────────────────────
val SurfaceCard = Color(0xFFFFFFFF)
val SurfaceSheet = Color(0xFFF5F6F3)
val SurfaceChipTrack = Color(0xFFEAEDE9)
val SurfaceTile = Color(0xFFF1F3F0)
val DividerSoft = Color(0xFFEEF1EE)
val DividerStrong = Color(0xFFE2E7E4)
val OutlineSoft = Color(0xFFD6DEDC)

// ── Gold / primary CTA ──────────────────────────────────────────
val GoldTop = Color(0xFFFFDD66)
val GoldBottom = Color(0xFFF5B324)
val GoldPressedShadow = Color(0xFFC4841A)
val InkOnGold = Color(0xFF3A2405)

// ── Semantic ─────────────────────────────────────────────────────
val ToggleOn = Color(0xFF3FBF7F)
val ToggleOff = Color(0xFFD3DAD7)
val DangerOutline = Color(0xFFE6C9C4)
val DangerText = Color(0xFFC0533F)
val NewBestBg = Color(0xFFFFF1C9)
val NewBestText = Color(0xFF8A5A08)

// ── Scrims / shadows ──────────────────────────────────────────────
val ScrimDark = Color(0xFF08222A)
val HudShadow = Color(0xFF092830)

// ── Pillar (pipe) gradient stops ───────────────────────────────
val PillarShaftStart = Color(0xFF3E9B62)
val PillarShaftMid1 = Color(0xFF7ADCA0) // 26%
val PillarShaftMid2 = Color(0xFF4FB076) // 62%
val PillarShaftEnd = Color(0xFF2F7C50)
val PillarCapStart = Color(0xFF357F52)
val PillarCapMid1 = Color(0xFF8AE3AC) // 24%
val PillarCapMid2 = Color(0xFF54B87C) // 60%
val PillarCapEnd = Color(0xFF2A6B47)
val PillarEdgeShade = Color(0xFF13402A) // used with ~0.18-0.22 alpha

// ── Ball (bird) gradient stops ─────────────────────────────────
val BallHighlight = Color(0xFFFFF3BE)
val BallMid = Color(0xFFFFD447) // 38%
val BallDark = Color(0xFFF2A413) // 78%
val BallShadow = Color(0xFFD0820C)
val BallInnerShade = Color(0xFFA05A00) // used with ~0.28 alpha

/** One sky's full set of background gradient/terrain colors. */
data class PaletteColors(
    val top: Color,
    val bottom: Color,
    val hill: Color,
    val hill2: Color,
    val cloud: Color,
    val ground: Color,
    val groundDark: Color,
    val groundEdge: Color
)

val DayPalette = PaletteColors(
    top = Color(0xFFA9E6EE),
    bottom = Color(0xFF5BB9CC),
    hill = Color(0xFF4C9FB2),
    hill2 = Color(0xFF3E8FA3),
    cloud = Color.White.copy(alpha = 0.55f),
    ground = Color(0xFFE7D6A4),
    groundDark = Color(0xFFC9B37C),
    groundEdge = Color(0xFFB39C63)
)

val DawnPalette = PaletteColors(
    top = Color(0xFFFFD3B0),
    bottom = Color(0xFFF1908A),
    hill = Color(0xFFC97A85),
    hill2 = Color(0xFFA9616F),
    cloud = Color.White.copy(alpha = 0.5f),
    ground = Color(0xFFEBCFA6),
    groundDark = Color(0xFFCBA97E),
    groundEdge = Color(0xFFB08F66)
)

val DuskPalette = PaletteColors(
    top = Color(0xFF2E5A72),
    bottom = Color(0xFF173C50),
    hill = Color(0xFF123243),
    hill2 = Color(0xFF0D2836),
    cloud = Color.White.copy(alpha = 0.18f),
    ground = Color(0xFF2B4250),
    groundDark = Color(0xFF1E323E),
    groundEdge = Color(0xFF152731)
)

fun paletteFor(sky: SkyPalette): PaletteColors = when (sky) {
    SkyPalette.DAY -> DayPalette
    SkyPalette.DAWN -> DawnPalette
    SkyPalette.DUSK -> DuskPalette
}
