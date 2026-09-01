package com.prajwalhs.flappybird.presentation.navigation

sealed class Screen(val route: String) {
    data object Menu : Screen("menu")
    data object Game : Screen("game")
    data object Settings : Screen("settings")
}