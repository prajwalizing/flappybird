package com.prajwalhs.flappybird.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.prajwalhs.flappybird.di.ScorePrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.scoreDataStore by preferencesDataStore(name = "score_prefs")


@Singleton
class ScoreDataStore @Inject constructor(
    @ScorePrefs private val dataStore: DataStore<Preferences>
) {
    private val HIGH_SCORE_KEY = intPreferencesKey("high_score")

    val highScore: Flow<Int> = dataStore.data.map { prefs ->
        prefs[HIGH_SCORE_KEY] ?: 0
    }

    suspend fun saveHighScoreIfBetter(score: Int): Boolean {
        var isNewHigh = false
        dataStore.edit { prefs ->
            val current = prefs[HIGH_SCORE_KEY] ?: 0
            if (score > current) {
                prefs[HIGH_SCORE_KEY] = score
                isNewHigh = true
            }
        }
        return isNewHigh
    }

    suspend fun resetHighScore() {
        dataStore.edit { it[HIGH_SCORE_KEY] = 0 }
    }
}