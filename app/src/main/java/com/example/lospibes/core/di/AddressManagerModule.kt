package com.example.lospibes.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.lospibes.core.data.repository.AddressManagerRepositoryImpl
import com.example.lospibes.core.domain.repository.AddressManagerRepository
import com.example.lospibes.core.domain.use_case.preference.address.AddressManagerUseCase
import com.example.lospibes.core.domain.use_case.preference.address.DeleteAddressManager
import com.example.lospibes.core.domain.use_case.preference.address.GetAddressManager
import com.example.lospibes.core.domain.use_case.preference.address.SetAddressManager
import com.example.lospibes.utils.Constants.ADDRESS_PREFERENCES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddressManagerModule {
    @Singleton
    @Provides
    fun provideAddressManager(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { context.preferencesDataStoreFile(ADDRESS_PREFERENCES) }
        )
    }

    @Singleton
    @Provides
    fun provideAddressManagerRepository(
        dataStore: DataStore<Preferences>
    ): AddressManagerRepository {
        return AddressManagerRepositoryImpl(dataStore)
    }

    @Singleton
    @Provides
    fun provideAddressManagerUseCase(
        addressManagerRepository: AddressManagerRepository
    ): AddressManagerUseCase {
        return AddressManagerUseCase(
            getAddressManager = GetAddressManager(addressManagerRepository),
            setAddressManager = SetAddressManager(addressManagerRepository),
            deleteAddressManager = DeleteAddressManager(addressManagerRepository)
        )
    }
}