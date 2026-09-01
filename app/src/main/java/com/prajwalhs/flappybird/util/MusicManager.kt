package com.prajwalhs.flappybird.util

import android.content.Context
import android.media.MediaPlayer
import com.prajwalhs.flappybird.R

class MusicManager(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null
    var enabled: Boolean = true

    fun start() {
        if (!enabled) return
        if (mediaPlayer == null) {
            // NOTE: add bg_music.mp3 to res/raw before this compiles
            mediaPlayer = MediaPlayer.create(context, R.raw.bg_music).apply {
                isLooping = true
            }
        }
        mediaPlayer?.start()
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
