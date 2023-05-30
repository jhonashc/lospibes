package com.example.lospibes.features.home.data.repository

import com.example.lospibes.features.home.data.dto.query.GetAddressQueryDto
import com.example.lospibes.features.home.data.dto.response.AddressesResponse
import com.example.lospibes.features.home.data.source.remote.AddressService
import com.example.lospibes.features.home.domain.repository.AddressRepository
import com.example.lospibes.utils.BaseApiResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val addressService: AddressService
) : AddressRepository, BaseApiResponse() {
    override fun getAddresses(
        userId: String,
        getAddressQueryDto: GetAddressQueryDto?
    ): Flow<NetworkResult<AddressesResponse>> {
        return safeApiCall {
            addressService.getAddresses(
                userId = userId,
                limit = getAddressQueryDto?.limit,
                offset = getAddressQueryDto?.offset
            )
        }
    }
}