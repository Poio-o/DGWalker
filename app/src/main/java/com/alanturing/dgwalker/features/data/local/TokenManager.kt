package com.alanturing.dgwalker.features.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.alanturing.dgwalker.features.data.remote.dto.UserDto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

@Singleton
class TokenManager @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        private val USER_CREATED_AT_KEY = stringPreferencesKey("user_created_at")
    }

    val tokenFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[TOKEN_KEY]
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    fun getToken(): Flow<String?> = tokenFlow

    suspend fun getTokenSync(): String? {
        return tokenFlow.firstOrNull()
    }

    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences.remove(USER_ID_KEY)
            preferences.remove(USER_NAME_KEY)
            preferences.remove(USER_EMAIL_KEY)
            preferences.remove(USER_CREATED_AT_KEY)
        }
    }

    suspend fun saveUser(user: UserDto) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = user.id
            preferences[USER_NAME_KEY] = user.username
            preferences[USER_EMAIL_KEY] = user.email
            preferences[USER_CREATED_AT_KEY] = user.createdAt
        }
    }

    fun getUser(): Flow<UserDto?> = context.dataStore.data.map { preferences ->
        val id = preferences[USER_ID_KEY] ?: return@map null
        val username = preferences[USER_NAME_KEY] ?: ""
        val email = preferences[USER_EMAIL_KEY] ?: ""
        val createdAt = preferences[USER_CREATED_AT_KEY] ?: ""
        UserDto(id = id, username = username, email = email, createdAt = createdAt)
    }
}
