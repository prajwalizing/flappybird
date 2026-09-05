package com.prajwalhs.flappybird.data.repository

import com.prajwalhs.flappybird.data.local.ScoreDataStore
import com.prajwalhs.flappybird.domain.repository.ScoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScoreRepositoryImpl @Inject constructor(
    private val scoreDataStore: ScoreDataStore
) : ScoreRepository {
    override val highScore: Flow<Int> = scoreDataStore.highScore

    override suspend fun saveHighScoreIfBetter(score: Int): Boolean =
        scoreDataStore.saveHighScoreIfBetter(score)

    override suspend fun resetHighScore() =
        scoreDataStore.resetHighScore()
}