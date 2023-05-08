package com.example.lospibes.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.lospibes.core.data.repository.AuthPreferenceRepositoryImpl
import com.example.lospibes.core.domain.repository.AuthPreferenceRepository
import com.example.lospibes.core.domain.use_case.auth_preference.AuthPreferenceUseCase
import com.example.lospibes.core.domain.use_case.auth_preference.GetAccessToken
import com.example.lospibes.core.domain.use_case.auth_preference.GetRefreshToken
import com.example.lospibes.core.domain.use_case.auth_preference.GetUserId
import com.example.lospibes.core.domain.use_case.auth_preference.SetAccessToken
import com.example.lospibes.core.domain.use_case.auth_preference.SetRefreshToken
import com.example.lospibes.core.domain.use_case.auth_preference.SetUserId
import com.example.lospibes.utils.Constants.AUTH_PREFERENCES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthPreferenceModule {
    @Singleton
    @Provides
    fun provideAuthPreference(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { context.preferencesDataStoreFile(AUTH_PREFERENCES) }
        )
    }

    @Singleton
    @Provides
    fun provideAuthPreferenceRepository(
        authDataStore: DataStore<Preferences>
    ): AuthPreferenceRepository {
        return AuthPreferenceRepositoryImpl(authDataStore)
    }

    @Singleton
    @Provides
    fun provideAuthPreferenceUseCase(
        authPreferenceRepository: AuthPreferenceRepository
    ): AuthPreferenceUseCase {
        return AuthPreferenceUseCase(
            getAccessToken = GetAccessToken(authPreferenceRepository),
            getRefreshToken = GetRefreshToken(authPreferenceRepository),
            getUserId = GetUserId(authPreferenceRepository),
            setAccessToken = SetAccessToken(authPreferenceRepository),
            setRefreshToken = SetRefreshToken(authPreferenceRepository),
            setUserId = SetUserId(authPreferenceRepository)
        )
    }
}