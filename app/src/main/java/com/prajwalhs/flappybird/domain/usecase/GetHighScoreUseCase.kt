package com.prajwalhs.flappybird.domain.usecase

import com.prajwalhs.flappybird.domain.repository.ScoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHighScoreUseCase @Inject constructor(
    private val repository: ScoreRepository
) {
    operator fun invoke(): Flow<Int> = repository.highScore
}