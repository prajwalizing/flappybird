package com.prajwalhs.flappybird.presentation.features.menu

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.prajwalhs.flappybird.presentation.components.BackgroundCanvas
import com.prajwalhs.flappybird.presentation.components.GoldButton
import com.prajwalhs.flappybird.presentation.components.GroundCanvas
import com.prajwalhs.flappybird.presentation.components.OutlineButton
import com.prajwalhs.flappybird.presentation.theme.DayPalette
import com.prajwalhs.flappybird.presentation.theme.HudShadow
import com.prajwalhs.flappybird.presentation.theme.IBMPlexMonoFontFamily
import com.prajwalhs.flappybird.presentation.theme.OutfitFontFamily

@Composable
fun MenuScreen(
    onPlayClick: () -> Unit,
    onSettingsClick: () -> Unit,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val highScore by viewModel.highScore.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            BackgroundCanvas(this, DayPalette)
            GroundCanvas(this, size.height, DayPalette)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 34.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "ONE TAP · OFFLINE",
                style = TextStyle(
                    fontFamily = IBMPlexMonoFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    letterSpacing = 0.34.em,
                    color = Color.White.copy(alpha = 0.78f)
                )
            )
            Spacer(Modifier.height(14.dp))
            Text(
                text = "FLAPP",
                style = TextStyle(
                    fontFamily = OutfitFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 72.sp,
                    letterSpacing = (-0.05).em,
                    color = Color.White,
                    shadow = Shadow(
                        color = HudShadow.copy(alpha = 0.22f),
                        offset = Offset(0f, 6f),
                        blurRadius = 0f
                    )
                )
            )
            Spacer(Modifier.height(22.dp))

            BestScorePill(highScore = highScore)

            Spacer(Modifier.height(52.dp))

            GoldButton(
                text = "Play",
                modifier = Modifier.widthIn(max = 250.dp),
                height = 60.dp,
                fontSize = 20.sp,
                onClick = onPlayClick
            )
            Spacer(Modifier.height(14.dp))
            OutlineButton(
                text = "Settings",
                modifier = Modifier.widthIn(max = 250.dp),
                height = 54.dp,
                borderColor = Color.White.copy(alpha = 0.55f),
                textColor = Color.White,
                onClick = onSettingsClick
            )

            Spacer(Modifier.height(64.dp))
            Text(
                text = "Tap to flap · hold to glide",
                style = TextStyle(
                    fontFamily = OutfitFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.72f)
                )
            )
        }
    }
}

@Composable
private fun BestScorePill(highScore: Int) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(HudShadow.copy(alpha = 0.26f))
            .padding(start = 14.dp, top = 9.dp, bottom = 9.dp, end = 18.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .height(22.dp)
                    .widthIn(min = 22.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Brush.linearGradient(listOf(Color(0xFFFFE07A), Color(0xFFF2A413))))
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = "BEST $highScore",
                style = TextStyle(
                    fontFamily = IBMPlexMonoFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    letterSpacing = 0.12.em,
                    color = Color.White
                )
            )
        }
    }
}
