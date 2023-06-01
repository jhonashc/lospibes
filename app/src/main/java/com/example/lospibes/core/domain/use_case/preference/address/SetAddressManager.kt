package com.example.lospibes.core.domain.use_case.preference.address

import com.example.lospibes.core.domain.repository.AddressManagerRepository
import com.example.lospibes.features.home.domain.model.Address

class SetAddressManager(
    private val addressPreferenceRepository: AddressManagerRepository
) {
    suspend operator fun invoke(address: Address) {
        return addressPreferenceRepository.setAddressManager(address)
    }
}