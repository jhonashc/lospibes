package com.example.lospibes.features.home.domain.use_case.address

import com.example.lospibes.features.home.data.dto.data.UpdateUserAddressDto
import com.example.lospibes.features.home.data.dto.response.AddressResponse
import com.example.lospibes.features.home.domain.repository.AddressRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class UpdateUserAddress(
    private val addressRepository: AddressRepository
) {
    operator fun invoke(
        userId: String,
        addressId: String,
        updateUserAddressDto: UpdateUserAddressDto
    ): Flow<NetworkResult<AddressResponse>> {
        return addressRepository.updateUserAddress(
            userId = userId,
            addressId = addressId,
            updateUserAddressDto = updateUserAddressDto
        )
    }
}