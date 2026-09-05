package com.prajwalhs.flappybird.domain.model

/**
 * Tunable constants for game feel. All values are in px/second (or px/second^2)
 * since physics is updated using real delta-time, not per-frame.
 */
data class GameConfig(
    val gravity: Float = 1200f,          // px/s^2, pulls bird down
    val flapImpulse: Float = -380f,      // px/s, instantaneous upward velocity on tap
    val maxFallSpeed: Float = 720f,      // terminal velocity clamp
    val holdRiseAcceleration: Float = 750f, // px/s^2, upward acceleration while the tap is held
    val maxRiseSpeed: Float = 650f,      // terminal upward velocity clamp while held
    val pipeSpeed: Float = 220f,         // px/s, leftward scroll speed
    val pipeWidth: Float = 110f,
    val pipeGapHeight: Float = 380f,
    val pipeSpawnIntervalPx: Float = 500f, // horizontal distance between pipes
    val groundHeight: Float = 120f,
    val birdRadius: Float = 30f
)