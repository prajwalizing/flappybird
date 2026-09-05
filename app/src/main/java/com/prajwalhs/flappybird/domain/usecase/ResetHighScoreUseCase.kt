package com.prajwalhs.flappybird.domain.usecase

import com.prajwalhs.flappybird.domain.repository.ScoreRepository
import javax.inject.Inject

class ResetHighScoreUseCase @Inject constructor(
    private val repository: ScoreRepository
) {
    suspend operator fun invoke() = repository.resetHighScore()
}
