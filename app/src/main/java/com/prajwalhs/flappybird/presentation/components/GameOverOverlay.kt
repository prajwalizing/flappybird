package com.prajwalhs.flappybird.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.prajwalhs.flappybird.presentation.theme.DividerStrong
import com.prajwalhs.flappybird.presentation.theme.GoldBottom
import com.prajwalhs.flappybird.presentation.theme.IBMPlexMonoFontFamily
import com.prajwalhs.flappybird.presentation.theme.InkLabel
import com.prajwalhs.flappybird.presentation.theme.InkPrimary
import com.prajwalhs.flappybird.presentation.theme.InkSecondary
import com.prajwalhs.flappybird.presentation.theme.NewBestBg
import com.prajwalhs.flappybird.presentation.theme.NewBestText
import com.prajwalhs.flappybird.presentation.theme.OutfitFontFamily
import com.prajwalhs.flappybird.presentation.theme.OutlineSoft
import com.prajwalhs.flappybird.presentation.theme.ScrimDark
import com.prajwalhs.flappybird.presentation.theme.SurfaceCard

@Composable
fun GameOverOverlay(
    score: Int,
    highScore: Int,
    isNewHighScore: Boolean,
    onRestart: () -> Unit,
    onMenu: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScrimDark.copy(alpha = 0.5f))
            .padding(horizontal = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 306.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp))
                .background(SurfaceCard.copy(alpha = 0.97f))
                .padding(start = 24.dp, end = 24.dp, top = 26.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isNewHighScore) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(NewBestBg)
                        .padding(horizontal = 13.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(7.dp)
                            .height(7.dp)
                            .clip(RoundedCornerShape(50))
                            .background(GoldBottom)
                    )
                    Spacer(Modifier.width(7.dp))
                    Text(
                        text = "NEW BEST",
                        style = TextStyle(
                            fontFamily = IBMPlexMonoFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp,
                            letterSpacing = 0.14.em,
                            color = NewBestText
                        )
                    )
                }
                Spacer(Modifier.height(12.dp))
            }

            Text(
                text = "Nice run",
                style = TextStyle(
                    fontFamily = OutfitFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp,
                    letterSpacing = (-0.025).em,
                    color = InkPrimary
                )
            )

            Spacer(Modifier.height(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "SCORE",
                        style = TextStyle(
                            fontFamily = IBMPlexMonoFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            letterSpacing = 0.16.em,
                            color = InkLabel
                        )
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "$score",
                        style = TextStyle(
                            fontFamily = OutfitFontFamily,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 56.sp,
                            letterSpacing = (-0.04).em,
                            color = InkPrimary
                        )
                    )
                }
                Spacer(Modifier.width(26.dp))
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(52.dp)
                        .background(DividerStrong)
                )
                Spacer(Modifier.width(26.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "BEST",
                        style = TextStyle(
                            fontFamily = IBMPlexMonoFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            letterSpacing = 0.16.em,
                            color = InkLabel
                        )
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "$highScore",
                        style = TextStyle(
                            fontFamily = OutfitFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 34.sp,
                            color = InkSecondary
                        )
                    )
                }
            }
            Spacer(Modifier.height(22.dp))

            GoldButton(text = "Play again", height = 56.dp, fontSize = 19.sp, onClick = onRestart)
            Spacer(Modifier.height(10.dp))
            OutlineButton(text = "Menu", height = 48.dp, borderColor = OutlineSoft, textColor = InkPrimary, onClick = onMenu)
        }
    }
}
