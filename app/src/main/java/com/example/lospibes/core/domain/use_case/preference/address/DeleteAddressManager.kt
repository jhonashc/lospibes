package com.example.lospibes.core.domain.use_case.preference.address

import com.example.lospibes.core.domain.repository.AddressManagerRepository

class DeleteAddressManager(
    private val addressPreferenceRepository: AddressManagerRepository
) {
    suspend operator fun invoke() {
        return addressPreferenceRepository.deleteAddressManager()
    }
}