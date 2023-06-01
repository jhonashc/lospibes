package com.example.lospibes.features.home.data.repository

import com.example.lospibes.features.home.data.dto.data.CreateUserAddressDto
import com.example.lospibes.features.home.data.dto.data.UpdateUserAddressDto
import com.example.lospibes.features.home.data.dto.query.GetAddressQueryDto
import com.example.lospibes.features.home.data.dto.response.AddressResponse
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

    override fun getUserAddress(
        userId: String,
        addressId: String
    ): Flow<NetworkResult<AddressResponse>> {
        return safeApiCall {
            addressService.getUserAddress(
                userId = userId,
                addressId = addressId
            )
        }
    }

    override fun createUserAddress(
        userId: String,
        createUserAddressDto: CreateUserAddressDto
    ): Flow<NetworkResult<AddressResponse>> {
        return safeApiCall {
            addressService.createUserAddress(
                userId = userId,
                createUserAddressDto = createUserAddressDto
            )
        }
    }

    override fun updateUserAddress(
        userId: String,
        addressId: String,
        updateUserAddressDto: UpdateUserAddressDto
    ): Flow<NetworkResult<AddressResponse>> {
        return safeApiCall {
            addressService.updateUserAddress(
                userId = userId,
                addressId = addressId,
                updateUserAddressDto = updateUserAddressDto
            )
        }
    }

    override fun deleteUserAddress(
        userId: String,
        addressId: String
    ): Flow<NetworkResult<AddressResponse>> {
        return safeApiCall {
            addressService.deleteUserAddress(
                userId = userId,
                addressId = addressId
            )
        }
    }
}