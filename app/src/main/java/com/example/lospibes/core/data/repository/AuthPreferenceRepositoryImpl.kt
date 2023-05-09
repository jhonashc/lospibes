package com.example.lospibes.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.lospibes.core.domain.model.Auth
import com.example.lospibes.core.domain.repository.AuthPreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthPreferenceRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AuthPreferenceRepository {
    private object PreferencesKeys {
        val AUTH_ACCESS_TOKEN_KEY = stringPreferencesKey("AUTH_ACCESS_TOKEN_KEY")
        val AUTH_REFRESH_TOKEN_KEY = stringPreferencesKey("AUTH_REFRESH_TOKEN_KEY")
        val AUTH_USER_ID_KEY = stringPreferencesKey("AUTH_USER_ID_KEY")
    }

    override fun getAuthPreference(): Flow<Auth> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { preferences ->
                Auth(
                    accessToken = preferences[PreferencesKeys.AUTH_ACCESS_TOKEN_KEY] ?: "",
                    refreshToken = preferences[PreferencesKeys.AUTH_REFRESH_TOKEN_KEY] ?: "",
                    userId = preferences[PreferencesKeys.AUTH_USER_ID_KEY] ?: ""
                )
            }
    }

    override suspend fun setAuthPreference(auth: Auth) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.AUTH_ACCESS_TOKEN_KEY] = auth.accessToken
            preferences[PreferencesKeys.AUTH_REFRESH_TOKEN_KEY] = auth.refreshToken
            preferences[PreferencesKeys.AUTH_USER_ID_KEY] = auth.userId
        }
    }
}