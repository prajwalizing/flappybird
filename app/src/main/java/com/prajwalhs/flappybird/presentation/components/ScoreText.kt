package com.prajwalhs.flappybird.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.prajwalhs.flappybird.presentation.theme.HudShadow
import com.prajwalhs.flappybird.presentation.theme.IBMPlexMonoFontFamily
import com.prajwalhs.flappybird.presentation.theme.OutfitFontFamily

@Composable
fun ScoreText(score: Int, highScore: Int, onBack: () -> Unit, onPause: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 26.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$score",
                style = TextStyle(
                    fontFamily = OutfitFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 68.sp,
                    letterSpacing = (-0.04).em,
                    color = Color.White,
                    shadow = Shadow(
                        color = HudShadow.copy(alpha = 0.28f),
                        offset = Offset(0f, 4f),
                        blurRadius = 0f
                    )
                )
            )
            Text(
                text = "BEST $highScore",
                style = TextStyle(
                    fontFamily = IBMPlexMonoFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    letterSpacing = 0.16.em,
                    color = Color.White.copy(alpha = 0.72f)
                )
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 22.dp, start = 20.dp)
                .size(40.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(HudShadow.copy(alpha = 0.28f))
                .clickable(onClick = onBack),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "‹",
                style = TextStyle(
                    fontFamily = OutfitFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 22.dp, end = 20.dp)
                .size(40.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(HudShadow.copy(alpha = 0.28f))
                .clickable(onClick = onPause),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PauseSingleBar()
            Spacer(Modifier.width(4.dp))
            PauseSingleBar()
        }
    }
}

@Composable
private fun PauseSingleBar() {
    Box(
        modifier = Modifier
            .size(width = 4.dp, height = 14.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(Color.White)
    )
}
