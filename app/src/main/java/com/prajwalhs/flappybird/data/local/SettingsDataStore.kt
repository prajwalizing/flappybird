package com.prajwalhs.flappybird.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.prajwalhs.flappybird.di.SettingsPrefs
import com.prajwalhs.flappybird.domain.model.Settings
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
    private val SKIN_KEY = stringPreferencesKey("selected_skin")

    val settings: Flow<Settings> = dataStore.data.map { prefs ->
        Settings(
            soundEnabled = prefs[SOUND_KEY] ?: true,
            musicEnabled = prefs[MUSIC_KEY] ?: true,
            selectedSkin = prefs[SKIN_KEY] ?: "default"
        )
    }

    suspend fun updateSoundEnabled(enabled: Boolean) {
        dataStore.edit { it[SOUND_KEY] = enabled }
    }

    suspend fun updateMusicEnabled(enabled: Boolean) {
        dataStore.edit { it[MUSIC_KEY] = enabled }
    }

    suspend fun updateSkin(skin: String) {
        dataStore.edit { it[SKIN_KEY] = skin }
    }
}