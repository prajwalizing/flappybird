package com.prajwalhs.flappybird.presentation.features.settings

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.prajwalhs.flappybird.domain.model.Difficulty
import com.prajwalhs.flappybird.domain.model.SkyPalette
import com.prajwalhs.flappybird.presentation.components.OutlineButton
import com.prajwalhs.flappybird.presentation.theme.DangerOutline
import com.prajwalhs.flappybird.presentation.theme.DangerText
import com.prajwalhs.flappybird.presentation.theme.IBMPlexMonoFontFamily
import com.prajwalhs.flappybird.presentation.theme.InkLabel
import com.prajwalhs.flappybird.presentation.theme.InkMuted
import com.prajwalhs.flappybird.presentation.theme.InkPrimary
import com.prajwalhs.flappybird.presentation.theme.OutfitFontFamily
import com.prajwalhs.flappybird.presentation.theme.SurfaceCard
import com.prajwalhs.flappybird.presentation.theme.SurfaceChipTrack
import com.prajwalhs.flappybird.presentation.theme.SurfaceSheet
import com.prajwalhs.flappybird.presentation.theme.ToggleOff
import com.prajwalhs.flappybird.presentation.theme.ToggleOn
import com.prajwalhs.flappybird.presentation.theme.paletteFor

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val settings by viewModel.settings.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceSheet)
    ) {
        Row(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 36.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(SurfaceChipTrack)
                    .clickable(onClick = onBackClick),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "‹",
                    style = TextStyle(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Medium, fontSize = 20.sp, color = InkPrimary)
                )
            }
            Spacer(Modifier.width(14.dp))
            Text(
                text = "Settings",
                style = TextStyle(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Bold, fontSize = 26.sp, letterSpacing = (-0.02).em, color = InkPrimary)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(26.dp)
        ) {
            Column {
                SectionLabel("AUDIO")
                Spacer(Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(22.dp))
                        .background(SurfaceCard)
                        .padding(horizontal = 18.dp)
                ) {
                    ToggleRow(
                        title = "Sound effects",
                        subtitle = "Flap, score and crash",
                        checked = settings.soundEnabled,
                        onCheckedChange = viewModel::toggleSound,
                        showDivider = true
                    )
                    ToggleRow(
                        title = "Music",
                        subtitle = "Low ambient loop",
                        checked = settings.musicEnabled,
                        onCheckedChange = viewModel::toggleMusic,
                        showDivider = false
                    )
                }
            }

            Column {
                SectionLabel("ACCESSIBILITY")
                Spacer(Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(22.dp))
                        .background(SurfaceCard)
                        .padding(horizontal = 18.dp)
                ) {
                    ToggleRow(
                        title = "Immersive mode",
                        subtitle = "Hide system bars while playing",
                        checked = settings.immersiveModeEnabled,
                        onCheckedChange = viewModel::toggleImmersiveMode,
                        showDivider = false
                    )
                }
            }

            Column {
                SectionLabel("DIFFICULTY")
                Spacer(Modifier.height(10.dp))
                DifficultySelector(selected = settings.difficulty, onSelect = viewModel::setDifficulty)
                Spacer(Modifier.height(9.dp))
                Text(
                    text = difficultyHint(settings.difficulty),
                    style = TextStyle(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Normal, fontSize = 13.sp, lineHeight = 18.sp, color = InkMuted)
                )
            }

            Column {
                SectionLabel("SKY")
                Spacer(Modifier.height(10.dp))
                SkySelector(selected = settings.sky, onSelect = viewModel::setSky)
            }

            OutlineButton(
                text = "Reset best score",
                height = 52.dp,
                borderColor = DangerOutline,
                textColor = DangerText,
                onClick = viewModel::resetBestScore
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = IBMPlexMonoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            letterSpacing = 0.18.em,
            color = InkMuted
        )
    )
}

private fun difficultyHint(difficulty: Difficulty): String = when (difficulty) {
    Difficulty.CHILL -> "Wider gaps, slower drift — good for a first run."
    Difficulty.CLASSIC -> "The original rhythm: 210dp gap."
    Difficulty.STORM -> "Tight gaps and faster scroll for the leaderboard."
}

@Composable
private fun ToggleRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    showDivider: Boolean
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = TextStyle(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Medium, fontSize = 16.sp, color = InkPrimary))
                Spacer(Modifier.height(3.dp))
                Text(text = subtitle, style = TextStyle(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Normal, fontSize = 13.sp, color = InkMuted))
            }
            SettingsToggle(checked = checked, onCheckedChange = onCheckedChange)
        }
        if (showDivider) {
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFEEF1EE)))
        }
    }
}

@Composable
private fun SettingsToggle(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    val trackColor by animateColorAsState(if (checked) ToggleOn else ToggleOff, label = "toggleTrack")
    val knobOffset by animateDpAsState(if (checked) 20.dp else 0.dp, label = "toggleKnob")

    Box(
        modifier = Modifier
            .size(width = 52.dp, height = 32.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(trackColor)
            .clickable { onCheckedChange(!checked) }
            .padding(3.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(start = knobOffset)
                .size(26.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
    }
}

@Composable
private fun DifficultySelector(selected: Difficulty, onSelect: (Difficulty) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(SurfaceChipTrack)
            .padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Difficulty.entries.forEach { difficulty ->
            val isSelected = difficulty == selected
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(if (isSelected) SurfaceCard else Color.Transparent)
                    .clickable { onSelect(difficulty) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = difficulty.name.lowercase().replaceFirstChar { it.uppercase() },
                    style = TextStyle(
                        fontFamily = OutfitFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = if (isSelected) InkPrimary else InkLabel
                    )
                )
            }
        }
    }
}

@Composable
private fun SkySelector(selected: SkyPalette, onSelect: (SkyPalette) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        SkyPalette.entries.forEach { sky ->
            val isSelected = sky == selected
            val palette = paletteFor(sky)
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Brush.verticalGradient(listOf(palette.top, palette.bottom)))
                        .border(
                            width = if (isSelected) 3.dp else 0.dp,
                            color = if (isSelected) InkPrimary else Color.Transparent,
                            shape = RoundedCornerShape(18.dp)
                        )
                        .clickable { onSelect(sky) }
                )
                Spacer(Modifier.height(7.dp))
                Text(
                    text = sky.name.lowercase().replaceFirstChar { it.uppercase() },
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = OutfitFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = if (isSelected) InkPrimary else InkLabel
                    )
                )
            }
        }
    }
}
