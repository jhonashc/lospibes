package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.data.dto.query.GetAddressQueryDto
import com.example.lospibes.features.home.data.dto.response.AddressesResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    fun getAddresses(
        userId: String,
        getAddressQueryDto: GetAddressQueryDto? = null
    ): Flow<NetworkResult<AddressesResponse>>
}