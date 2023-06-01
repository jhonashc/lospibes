package com.example.lospibes.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.lospibes.core.data.repository.AuthManagerRepositoryImpl
import com.example.lospibes.core.domain.repository.AuthManagerRepository
import com.example.lospibes.core.domain.use_case.preference.auth.AuthManagerUseCase
import com.example.lospibes.core.domain.use_case.preference.auth.DeleteAuthManager
import com.example.lospibes.core.domain.use_case.preference.auth.GetAuthManager
import com.example.lospibes.core.domain.use_case.preference.auth.SetAuthManager
import com.example.lospibes.utils.Constants.AUTH_PREFERENCES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthManagerModule {
    @Singleton
    @Provides
    @Named
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
        dataStore: DataStore<Preferences>
    ): AuthManagerRepository {
        return AuthManagerRepositoryImpl(dataStore)
    }

    @Singleton
    @Provides
    fun provideAuthPreferenceUseCase(
        authPreferenceRepository: AuthManagerRepository
    ): AuthManagerUseCase {
        return AuthManagerUseCase(
            getAuthManager = GetAuthManager(authPreferenceRepository),
            setAuthManager = SetAuthManager(authPreferenceRepository),
            deleteAuthManager = DeleteAuthManager(authPreferenceRepository)
        )
    }
}