package com.example.lospibes.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.lospibes.core.domain.repository.AddressManagerRepository
import com.example.lospibes.features.home.domain.model.Address
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddressManagerRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AddressManagerRepository {
    private object PreferencesKeys {
        val ADDRESS_ID_KEY = stringPreferencesKey("ADDRESS_ID_KEY")
        val ADDRESS_NAME_KEY = stringPreferencesKey("ADDRESS_NAME_KEY")
    }

    override fun getAddressManager(): Flow<Address> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { preferences ->
                Address(
                    id = preferences[PreferencesKeys.ADDRESS_ID_KEY] ?: "",
                    name = preferences[PreferencesKeys.ADDRESS_NAME_KEY] ?: "",
                    sideStreet = "",
                    deliveryInstruction = ""
                )
            }.flowOn(Dispatchers.IO)
    }

    override suspend fun setAddressManager(address: Address) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ADDRESS_ID_KEY] = address.id
            preferences[PreferencesKeys.ADDRESS_NAME_KEY] = address.name
        }
    }

    override suspend fun deleteAddressManager() {
        dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.ADDRESS_ID_KEY)
            preferences.remove(PreferencesKeys.ADDRESS_NAME_KEY)
        }
    }
}