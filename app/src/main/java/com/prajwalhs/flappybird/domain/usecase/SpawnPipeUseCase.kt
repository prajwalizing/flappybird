package com.prajwalhs.flappybird.domain.usecase

import com.prajwalhs.flappybird.domain.model.GameConfig
import com.prajwalhs.flappybird.domain.model.Pipe
import javax.inject.Inject
import kotlin.random.Random

class SpawnPipeUseCase @Inject constructor() {
    operator fun invoke(
        spawnX: Float,
        screenHeight: Float,
        config: GameConfig
    ): Pipe {
        val minGapCenter = config.pipeGapHeight / 2f + 80f
        val maxGapCenter = screenHeight - config.groundHeight - config.pipeGapHeight / 2f - 80f
        val gapCenterY = Random.nextFloat() * (maxGapCenter - minGapCenter) + minGapCenter

        return Pipe(
            x = spawnX,
            gapCenterY = gapCenterY,
            gapHeight = config.pipeGapHeight,
            width = config.pipeWidth
        )
    }
}