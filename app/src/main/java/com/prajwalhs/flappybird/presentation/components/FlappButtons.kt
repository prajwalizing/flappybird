package com.prajwalhs.flappybird.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prajwalhs.flappybird.presentation.theme.GoldBottom
import com.prajwalhs.flappybird.presentation.theme.GoldPressedShadow
import com.prajwalhs.flappybird.presentation.theme.GoldTop
import com.prajwalhs.flappybird.presentation.theme.InkOnGold
import com.prajwalhs.flappybird.presentation.theme.InkPrimary
import com.prajwalhs.flappybird.presentation.theme.OutfitFontFamily
import com.prajwalhs.flappybird.presentation.theme.OutlineSoft

/** Primary gold CTA with a solid-shadow "keycap" bevel, matching the design's flat 3D buttons. */
@Composable
fun GoldButton(
    text: String,
    modifier: Modifier = Modifier,
    height: Dp = 56.dp,
    fontSize: androidx.compose.ui.unit.TextUnit = 18.sp,
    onClick: () -> Unit
) {
    val bevel = 5.dp
    Box(modifier = modifier.fillMaxWidth().height(height + bevel)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height + bevel)
                .clip(RoundedCornerShape(18.dp))
                .background(GoldPressedShadow)
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(height)
                .clip(RoundedCornerShape(18.dp))
                .background(Brush.verticalGradient(listOf(GoldTop, GoldBottom)))
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontFamily = OutfitFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize,
                    color = InkOnGold
                )
            )
        }
    }
}

/** Secondary outline button — used for Menu/Restart/Settings actions. */
@Composable
fun OutlineButton(
    text: String,
    modifier: Modifier = Modifier,
    height: Dp = 48.dp,
    borderColor: Color = OutlineSoft,
    textColor: Color = InkPrimary,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(16.dp))
            .border(1.5.dp, borderColor, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = OutfitFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = textColor
            )
        )
    }
}
