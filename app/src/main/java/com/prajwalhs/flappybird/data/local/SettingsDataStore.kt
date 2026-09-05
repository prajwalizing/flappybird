package com.prajwalhs.flappybird.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.prajwalhs.flappybird.di.SettingsPrefs
import com.prajwalhs.flappybird.domain.model.Difficulty
import com.prajwalhs.flappybird.domain.model.Settings
import com.prajwalhs.flappybird.domain.model.SkyPalette
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.settingsDataStore by preferencesDataStore(name = "settings_prefs")

@Singleton
class SettingsDataStore @Inject constructor(
    @SettingsPrefs private val dataStore: DataStore<Preferences>
) {
    private val SOUND_KEY = booleanPreferencesKey("sound_enabled")
    private val MUSIC_KEY = booleanPreferencesKey("music_enabled")
    private val DIFFICULTY_KEY = stringPreferencesKey("difficulty")
    private val SKY_KEY = stringPreferencesKey("sky")
    private val IMMERSIVE_MODE_KEY = booleanPreferencesKey("immersive_mode_enabled")

    val settings: Flow<Settings> = dataStore.data.map { prefs ->
        Settings(
            soundEnabled = prefs[SOUND_KEY] ?: true,
            musicEnabled = prefs[MUSIC_KEY] ?: true,
            difficulty = prefs[DIFFICULTY_KEY]?.let { runCatching { Difficulty.valueOf(it) }.getOrNull() }
                ?: Difficulty.CLASSIC,
            sky = prefs[SKY_KEY]?.let { runCatching { SkyPalette.valueOf(it) }.getOrNull() }
                ?: SkyPalette.DAY,
            immersiveModeEnabled = prefs[IMMERSIVE_MODE_KEY] ?: true
        )
    }

    suspend fun updateSoundEnabled(enabled: Boolean) {
        dataStore.edit { it[SOUND_KEY] = enabled }
    }

    suspend fun updateMusicEnabled(enabled: Boolean) {
        dataStore.edit { it[MUSIC_KEY] = enabled }
    }

    suspend fun updateImmersiveModeEnabled(enabled: Boolean) {
        dataStore.edit { it[IMMERSIVE_MODE_KEY] = enabled }
    }

    suspend fun updateDifficulty(difficulty: Difficulty) {
        dataStore.edit { it[DIFFICULTY_KEY] = difficulty.name }
    }

    suspend fun updateSky(sky: SkyPalette) {
        dataStore.edit { it[SKY_KEY] = sky.name }
    }
}