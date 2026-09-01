package com.prajwalhs.flappybird.presentation.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.prajwalhs.flappybird.presentation.theme.SkyBlue

@Composable
fun MenuScreen(
    onPlayClick: () -> Unit,
    onSettingsClick: () -> Unit,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val highScore by viewModel.highScore.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SkyBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Flappy Bird", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Best: $highScore", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onPlayClick) { Text("Play") }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onSettingsClick) { Text("Settings") }
    }
}