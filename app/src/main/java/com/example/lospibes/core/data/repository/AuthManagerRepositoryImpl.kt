package com.example.lospibes.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.lospibes.core.domain.model.Auth
import com.example.lospibes.core.domain.repository.AuthManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthManagerRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AuthManagerRepository {
    private object PreferencesKeys {
        val AUTH_ACCESS_TOKEN_KEY = stringPreferencesKey("AUTH_ACCESS_TOKEN_KEY")
        val AUTH_REFRESH_TOKEN_KEY = stringPreferencesKey("AUTH_REFRESH_TOKEN_KEY")
        val AUTH_USER_ID_KEY = stringPreferencesKey("AUTH_USER_ID_KEY")
    }

    override fun getAuthManager(): Flow<Auth> {
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
            }.flowOn(Dispatchers.IO)
    }

    override suspend fun setAuthManager(auth: Auth) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.AUTH_ACCESS_TOKEN_KEY] = auth.accessToken
            preferences[PreferencesKeys.AUTH_REFRESH_TOKEN_KEY] = auth.refreshToken
            preferences[PreferencesKeys.AUTH_USER_ID_KEY] = auth.userId
        }
    }

    override suspend fun deleteAuthManager() {
        dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.AUTH_ACCESS_TOKEN_KEY)
            preferences.remove(PreferencesKeys.AUTH_REFRESH_TOKEN_KEY)
            preferences.remove(PreferencesKeys.AUTH_USER_ID_KEY)
        }
    }
}