package com.example.lospibes.core.domain.use_case.preference.address

import com.example.lospibes.core.domain.repository.AddressManagerRepository
import com.example.lospibes.features.home.domain.model.Address
import kotlinx.coroutines.flow.Flow

class GetAddressManager(
    private val addressPreferenceRepository: AddressManagerRepository
) {
    operator fun invoke(): Flow<Address> {
        return addressPreferenceRepository.getAddressManager()
    }
}