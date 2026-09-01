package com.prajwalhs.flappybird.domain.model

/**
 * Tunable constants for game feel. All values are in px/second (or px/second^2)
 * since physics is updated using real delta-time, not per-frame.
 */
data class GameConfig(
    val gravity: Float = 1500f,          // px/s^2, pulls bird down
    val flapImpulse: Float = -520f,      // px/s, instantaneous upward velocity on tap
    val maxFallSpeed: Float = 900f,      // terminal velocity clamp
    val pipeSpeed: Float = 220f,         // px/s, leftward scroll speed
    val pipeWidth: Float = 110f,
    val pipeGapHeight: Float = 380f,
    val pipeSpawnIntervalPx: Float = 500f, // horizontal distance between pipes
    val groundHeight: Float = 120f,
    val birdRadius: Float = 30f
)