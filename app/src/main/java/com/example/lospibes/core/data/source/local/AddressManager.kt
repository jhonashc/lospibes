package com.example.lospibes.core.data.source.local

import com.example.lospibes.features.home.domain.model.Address
import kotlinx.coroutines.flow.Flow

interface AddressManager {
    fun getAddressManager(): Flow<Address>
    suspend fun setAddressManager(address: Address)
    suspend fun deleteAddressManager()
}