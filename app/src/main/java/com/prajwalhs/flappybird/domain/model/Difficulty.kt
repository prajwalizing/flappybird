package com.prajwalhs.flappybird.domain.model

enum class Difficulty(val pipeGapHeight: Float, val pipeSpeed: Float) {
    CHILL(pipeGapHeight = 250f, pipeSpeed = 130f),
    CLASSIC(pipeGapHeight = 210f, pipeSpeed = 158f),
    STORM(pipeGapHeight = 190f, pipeSpeed = 176f)
}
