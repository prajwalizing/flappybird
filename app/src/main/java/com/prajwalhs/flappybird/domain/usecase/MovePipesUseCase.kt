package com.prajwalhs.flappybird.domain.usecase

import com.prajwalhs.flappybird.domain.model.GameConfig
import com.prajwalhs.flappybird.domain.model.Pipe
import javax.inject.Inject

/**
 * Scrolls all pipes left, drops pipes that have scrolled off screen,
 * and spawns a new pipe once the rightmost pipe has moved in far enough.
 */
class MovePipesUseCase @Inject constructor(
    private val spawnPipeUseCase: SpawnPipeUseCase
) {
    operator fun invoke(
        pipes: List<Pipe>,
        config: GameConfig,
        deltaTimeSeconds: Float,
        screenWidth: Float,
        screenHeight: Float
    ): List<Pipe> {
        val moved = pipes
            .map { it.copy(x = it.x - config.pipeSpeed * deltaTimeSeconds) }
            .filter { it.x + it.width > -50f } // drop off-screen pipes

        val rightmostX = moved.maxOfOrNull { it.x } ?: (screenWidth)
        val needsNewPipe = moved.isEmpty() || (screenWidth - rightmostX) >= config.pipeSpawnIntervalPx

        return if (needsNewPipe) {
            moved + spawnPipeUseCase(
                spawnX = screenWidth + config.pipeWidth,
                screenHeight = screenHeight,
                config = config
            )
        } else {
            moved
        }
    }
}