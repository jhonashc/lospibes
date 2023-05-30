package com.example.lospibes.features.home.data.source.remote

import com.example.lospibes.features.home.data.dto.response.AddressesResponse
import com.example.lospibes.utils.Constants.USERS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AddressService {
    @GET("${USERS}/{userId}/addresses")
    suspend fun getAddresses(
        @Path("userId") userId: String,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<AddressesResponse>
}