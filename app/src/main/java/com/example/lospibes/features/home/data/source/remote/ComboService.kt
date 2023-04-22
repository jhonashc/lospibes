package com.example.lospibes.features.home.data.source.remote

import com.example.lospibes.features.home.data.dto.response.ComboResponse
import com.example.lospibes.features.home.data.dto.response.CombosResponse
import com.example.lospibes.utils.Constants.COMBOS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComboService {
    @GET(COMBOS)
    suspend fun getCombos(
        @Query("name") name: String? = null,
        @Query("min") min: Int? = null,
        @Query("max") max: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<CombosResponse>

    @GET("${COMBOS}/{id}")
    suspend fun getComboById(
        @Path("id") id: String
    ): Response<ComboResponse>
}