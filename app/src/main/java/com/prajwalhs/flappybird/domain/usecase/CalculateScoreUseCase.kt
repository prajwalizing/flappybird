package com.prajwalhs.flappybird.domain.usecase

import com.prajwalhs.flappybird.domain.model.Bird
import com.prajwalhs.flappybird.domain.model.Pipe
import javax.inject.Inject

class CalculateScoreUseCase @Inject constructor() {
    /**
     * Marks pipes as "passed" once the bird has flown past their right edge,
     * and returns the updated pipe list plus how much score to add this call.
     */
    operator fun invoke(bird: Bird, pipes: List<Pipe>): Pair<List<Pipe>, Int> {
        var scoreDelta = 0
        val updated = pipes.map { pipe ->
            if (!pipe.passed && (pipe.x + pipe.width) < bird.x) {
                scoreDelta += 1
                pipe.copy(passed = true)
            } else {
                pipe
            }
        }
        return updated to scoreDelta
    }
}