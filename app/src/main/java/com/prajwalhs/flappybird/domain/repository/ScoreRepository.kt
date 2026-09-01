package com.prajwalhs.flappybird.domain.repository

import kotlinx.coroutines.flow.Flow

interface ScoreRepository {
    val highScore: Flow<Int>
    suspend fun saveHighScoreIfBetter(score: Int): Boolean // returns true if it was a new high score
}