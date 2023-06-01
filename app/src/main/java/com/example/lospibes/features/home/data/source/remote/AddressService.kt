package com.example.lospibes.features.home.data.source.remote

import com.example.lospibes.features.home.data.dto.data.CreateUserAddressDto
import com.example.lospibes.features.home.data.dto.data.UpdateUserAddressDto
import com.example.lospibes.features.home.data.dto.response.AddressResponse
import com.example.lospibes.features.home.data.dto.response.AddressesResponse
import com.example.lospibes.utils.Constants.USERS
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AddressService {
    @GET("${USERS}/{userId}/addresses")
    suspend fun getAddresses(
        @Path("userId") userId: String,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<AddressesResponse>

    @GET("${USERS}/{userId}/addresses/{addressId}")
    suspend fun getUserAddress(
        @Path("userId") userId: String,
        @Path("addressId") addressId: String
    ): Response<AddressResponse>

    @POST("${USERS}/{userId}/addresses")
    suspend fun createUserAddress(
        @Path("userId") userId: String,
        @Body createUserAddressDto: CreateUserAddressDto
    ): Response<AddressResponse>

    @PATCH("${USERS}/{userId}/addresses/{addressId}")
    suspend fun updateUserAddress(
        @Path("userId") userId: String,
        @Path("addressId") addressId: String,
        @Body updateUserAddressDto: UpdateUserAddressDto
    ): Response<AddressResponse>

    @DELETE("${USERS}/{userId}/addresses/{addressId}")
    suspend fun deleteUserAddress(
        @Path("userId") userId: String,
        @Path("addressId") addressId: String
    ): Response<AddressResponse>
}