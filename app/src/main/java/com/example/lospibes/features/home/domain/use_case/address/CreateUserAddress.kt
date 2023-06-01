package com.example.lospibes.features.home.domain.use_case.address

import com.example.lospibes.features.home.data.dto.data.CreateUserAddressDto
import com.example.lospibes.features.home.data.dto.response.AddressResponse
import com.example.lospibes.features.home.domain.repository.AddressRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class CreateUserAddress(
    private val addressRepository: AddressRepository
) {
    operator fun invoke(
        userId: String,
        createUserAddressDto: CreateUserAddressDto
    ): Flow<NetworkResult<AddressResponse>> {
        return addressRepository.createUserAddress(
            userId = userId,
            createUserAddressDto = createUserAddressDto
        )
    }
}