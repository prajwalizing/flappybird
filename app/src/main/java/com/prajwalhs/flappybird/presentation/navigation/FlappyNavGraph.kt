package com.prajwalhs.flappybird.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prajwalhs.flappybird.presentation.game.GameScreen
import com.prajwalhs.flappybird.presentation.menu.MenuScreen
import com.prajwalhs.flappybird.presentation.settings.SettingsScreen

@Composable
fun FlappyNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Menu.route,
        modifier = modifier
    ) {
        composable(Screen.Menu.route) {
            MenuScreen(
                onPlayClick = { navController.navigate(Screen.Game.route) },
                onSettingsClick = { navController.navigate(Screen.Settings.route) }
            )
        }
        composable(Screen.Game.route) {
            GameScreen(
                onBackToMenu = {
                    navController.popBackStack(Screen.Menu.route, inclusive = false)
                }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(onBackClick = { navController.popBackStack() })
        }
    }
}