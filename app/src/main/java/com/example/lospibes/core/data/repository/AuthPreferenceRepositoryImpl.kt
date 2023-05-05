package com.example.lospibes.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.lospibes.core.domain.repository.AuthPreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthPreferenceRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AuthPreferenceRepository {

    private object PreferencesKeys {
        val AUTH_TOKEN_KEY = stringPreferencesKey("AUTH_TOKEN_KEY")
        val AUTH_USER_ID_KEY = stringPreferencesKey("AUTH_USER_ID_KEY")
    }

    override fun getToken(): Flow<String> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { preferences ->
                preferences[PreferencesKeys.AUTH_TOKEN_KEY] ?: ""
            }
    }

    override fun getUserId(): Flow<String> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { preferences ->
                preferences[PreferencesKeys.AUTH_USER_ID_KEY] ?: ""
            }
    }

    override suspend fun setToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.AUTH_TOKEN_KEY] = token
        }
    }

    override suspend fun setUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.AUTH_USER_ID_KEY] = userId
        }
    }
}