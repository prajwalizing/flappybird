package com.prajwalhs.flappybird.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val settings by viewModel.settings.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {
        Text(text = "Settings", modifier = Modifier.padding(bottom = 24.dp))

        Row {
            Text(text = "Sound Effects", modifier = Modifier.padding(end = 12.dp))
            Switch(checked = settings.soundEnabled, onCheckedChange = { viewModel.toggleSound(it) })
        }

        Row {
            Text(text = "Music", modifier = Modifier.padding(end = 12.dp))
            Switch(checked = settings.musicEnabled, onCheckedChange = { viewModel.toggleMusic(it) })
        }

        Button(onClick = onBackClick, modifier = Modifier.padding(top = 24.dp)) {
            Text("Back")
        }
    }
}