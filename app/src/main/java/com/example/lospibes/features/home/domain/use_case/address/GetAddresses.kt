package com.example.lospibes.features.home.domain.use_case.address

import com.example.lospibes.features.home.data.dto.query.GetAddressQueryDto
import com.example.lospibes.features.home.data.dto.response.AddressesResponse
import com.example.lospibes.features.home.domain.repository.AddressRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetAddresses(
    private val addressRepository: AddressRepository
) {
    operator fun invoke(
        userId: String,
        getAddressQueryDto: GetAddressQueryDto? = null
    ): Flow<NetworkResult<AddressesResponse>> {
        return addressRepository.getAddresses(
            userId = userId,
            getAddressQueryDto = getAddressQueryDto
        )
    }
}