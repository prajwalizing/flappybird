package com.prajwalhs.flappybird.domain.usecase

import com.prajwalhs.flappybird.domain.model.Bird
import com.prajwalhs.flappybird.domain.model.GameConfig
import kotlin.math.min
import javax.inject.Inject
import kotlin.math.max

/**
 * Applies gravity + velocity to the bird for one frame, and derives rotation
 * from velocity for a nice "nose up on flap, nose down on fall" effect.
 *
 * While [isRising] is true (tap held down), upward acceleration replaces gravity so the
 * bird's rise speeds up the longer the hold lasts, instead of falling back between flaps.
 */
class UpdateBirdPhysicsUseCase @Inject constructor() {
    operator fun invoke(bird: Bird, config: GameConfig, deltaTimeSeconds: Float, isRising: Boolean): Bird {
        val newVelocity = if (isRising) {
            max(bird.velocityY - config.holdRiseAcceleration * deltaTimeSeconds, -config.maxRiseSpeed)
        } else {
            min(bird.velocityY + config.gravity * deltaTimeSeconds, config.maxFallSpeed)
        }
        val newY = bird.y + newVelocity * deltaTimeSeconds

        // Map velocity range to rotation: -520 (flap) -> -30deg, +900 (falling) -> +90deg
        val rotation = max(-30f, min(90f, newVelocity / 10f))

        return bird.copy(y = newY, velocityY = newVelocity, rotationDegrees = rotation)
    }

    fun applyFlap(bird: Bird, config: GameConfig): Bird {
        return bird.copy(velocityY = config.flapImpulse)
    }
}