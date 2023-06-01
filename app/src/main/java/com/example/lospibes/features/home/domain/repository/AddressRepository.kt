package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.data.dto.data.CreateUserAddressDto
import com.example.lospibes.features.home.data.dto.data.UpdateUserAddressDto
import com.example.lospibes.features.home.data.dto.query.GetAddressQueryDto
import com.example.lospibes.features.home.data.dto.response.AddressResponse
import com.example.lospibes.features.home.data.dto.response.AddressesResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    fun getAddresses(
        userId: String,
        getAddressQueryDto: GetAddressQueryDto? = null
    ): Flow<NetworkResult<AddressesResponse>>

    fun getUserAddress(
        userId: String,
        addressId: String
    ): Flow<NetworkResult<AddressResponse>>

    fun createUserAddress(
        userId: String,
        createUserAddressDto: CreateUserAddressDto
    ): Flow<NetworkResult<AddressResponse>>

    fun updateUserAddress(
        userId: String,
        addressId: String,
        updateUserAddressDto: UpdateUserAddressDto
    ): Flow<NetworkResult<AddressResponse>>

    fun deleteUserAddress(
        userId: String,
        addressId: String
    ): Flow<NetworkResult<AddressResponse>>
}