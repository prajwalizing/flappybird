package com.prajwalhs.flappybird.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.prajwalhs.flappybird.presentation.theme.IBMPlexMonoFontFamily
import com.prajwalhs.flappybird.presentation.theme.InkLabel
import com.prajwalhs.flappybird.presentation.theme.InkPrimary
import com.prajwalhs.flappybird.presentation.theme.OutfitFontFamily
import com.prajwalhs.flappybird.presentation.theme.OutlineSoft
import com.prajwalhs.flappybird.presentation.theme.ScrimDark
import com.prajwalhs.flappybird.presentation.theme.SurfaceCard
import com.prajwalhs.flappybird.presentation.theme.SurfaceTile

@Composable
fun PauseOverlay(
    score: Int,
    highScore: Int,
    onResume: () -> Unit,
    onRestart: () -> Unit,
    onMenu: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScrimDark.copy(alpha = 0.55f))
            .padding(horizontal = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 300.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(28.dp))
                .background(SurfaceCard.copy(alpha = 0.96f))
                .padding(horizontal = 24.dp, vertical = 28.dp)
        ) {
            Text(
                text = "PAUSED",
                style = TextStyle(
                    fontFamily = IBMPlexMonoFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    letterSpacing = 0.2.em,
                    color = InkLabel
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Take a breath",
                style = TextStyle(
                    fontFamily = OutfitFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    letterSpacing = (-0.02).em,
                    color = InkPrimary
                )
            )
            Spacer(Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                StatTile(label = "SCORE", value = "$score", modifier = Modifier.weight(1f))
                Spacer(Modifier.width(8.dp))
                StatTile(label = "BEST", value = "$highScore", modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.height(22.dp))

            GoldButton(text = "Resume", height = 54.dp, fontSize = 18.sp, onClick = onResume)
            Spacer(Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlineButton(
                    text = "Restart",
                    height = 48.dp,
                    borderColor = OutlineSoft,
                    textColor = InkPrimary,
                    modifier = Modifier.weight(1f),
                    onClick = onRestart
                )
                Spacer(Modifier.width(10.dp))
                OutlineButton(
                    text = "Menu",
                    height = 48.dp,
                    borderColor = OutlineSoft,
                    textColor = InkPrimary,
                    modifier = Modifier.weight(1f),
                    onClick = onMenu
                )
            }
        }
    }
}

@Composable
private fun StatTile(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceTile)
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = IBMPlexMonoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                letterSpacing = 0.14.em,
                color = InkLabel
            )
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = value,
            style = TextStyle(
                fontFamily = OutfitFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = InkPrimary
            )
        )
    }
}
