package com.alanturing.dgwalker.core.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.audioDataStore: androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "audio_prefs")

@Singleton
class SoundManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val audioAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()

    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(3)
        .setAudioAttributes(audioAttributes)
        .build()

    private val sfxMap = mutableMapOf<SfxType, Int>()

    private val sfxVolumeKey = floatPreferencesKey("sfx_volume")
    private val isMutedKey = booleanPreferencesKey("is_muted")

    init {
        loadSfx()
    }

    private fun loadSfx() {
        TODO("sfxMap[SfxType.CLICK] = soundPool.load(context.'rutaDelMP3')")
    }
    fun playSfx(type: SfxType) {
        if (getIsMuted()) return
        val soundId = sfxMap[type] ?: return
        val volume = getSfxVolumeSync()
        soundPool.play(soundId, volume, volume, 1, 0, 1.0f)
    }

    private fun getSfxVolumeSync(): Float{
        TODO("Not yet implemented")
    }

    private fun getIsMuted(): Boolean {
        TODO("Not yet implemented")
    }

    suspend fun setSfxVolume(volume: Float) {
        context.audioDataStore.edit { prefs -> prefs[sfxVolumeKey] = volume}
    }

    suspend fun setMuted(muted: Boolean){
        context.audioDataStore.edit { prefs -> prefs[isMutedKey] = muted}
    }

    val isMutedFlow: Flow<Boolean> = context.audioDataStore.data.map { prefs -> prefs[isMutedKey] ?: false }
    val sfxVolumeFlow: Flow<Float> = context.audioDataStore.data.map { prefs -> prefs[sfxVolumeKey] ?: 1.0f }

    enum class SfxType {
        CLICK,
        FEED,
        REWARD
    }
}