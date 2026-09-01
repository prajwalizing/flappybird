package com.prajwalhs.flappybird.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameOverOverlay(
    score: Int,
    highScore: Int,
    isNewHighScore: Boolean,
    onRestart: () -> Unit,
    onMenu: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Game Over", fontSize = 32.sp, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Score: $score", fontSize = 20.sp, color = Color.White)
        Text(text = "Best: $highScore", fontSize = 20.sp, color = Color.White)
        if (isNewHighScore) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "New High Score!", fontSize = 18.sp, color = Color.Yellow)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRestart) { Text("Restart") }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onMenu) { Text("Menu") }
    }
}