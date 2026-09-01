package com.prajwalhs.flappybird.domain.usecase

import com.prajwalhs.flappybird.domain.model.Bird
import com.prajwalhs.flappybird.domain.model.GameConfig
import com.prajwalhs.flappybird.domain.model.Pipe
import javax.inject.Inject

class CheckCollisionUseCase @Inject constructor() {
    operator fun invoke(
        bird: Bird,
        pipes: List<Pipe>,
        config: GameConfig,
        screenHeight: Float
    ): Boolean {
        // Ceiling
        if (bird.y - bird.radius <= 0f) return true

        // Ground
        if (bird.y + bird.radius >= screenHeight - config.groundHeight) return true

        // Pipes (simple circle vs rect AABB check per pipe column)
        for (pipe in pipes) {
            val birdLeft = bird.x - bird.radius
            val birdRight = bird.x + bird.radius
            val pipeLeft = pipe.x
            val pipeRight = pipe.x + pipe.width

            val horizontallyOverlapping = birdRight > pipeLeft && birdLeft < pipeRight
            if (!horizontallyOverlapping) continue

            val withinGap = (bird.y - bird.radius) > pipe.gapTopY && (bird.y + bird.radius) < pipe.gapBottomY
            if (!withinGap) return true
        }

        return false
    }
}