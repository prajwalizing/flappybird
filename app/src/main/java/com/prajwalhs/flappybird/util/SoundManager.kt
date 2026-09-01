package com.prajwalhs.flappybird.util

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.prajwalhs.flappybird.R

class SoundManager(context: Context) {

    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(4)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        )
        .build()

    // NOTE: add flap.mp3 / score.mp3 / hit.mp3 to res/raw before this compiles
    private val flapSoundId = soundPool.load(context, R.raw.flap, 1)
    private val scoreSoundId = soundPool.load(context, R.raw.score, 1)
    private val hitSoundId = soundPool.load(context, R.raw.hit, 1)

    var enabled: Boolean = true

    fun playFlap() { if (enabled) soundPool.play(flapSoundId, 1f, 1f, 1, 0, 1f) }
    fun playScore() { if (enabled) soundPool.play(scoreSoundId, 1f, 1f, 1, 0, 1f) }
    fun playHit() { if (enabled) soundPool.play(hitSoundId, 1f, 1f, 1, 0, 1f) }

    fun release() {
        soundPool.release()
    }
}