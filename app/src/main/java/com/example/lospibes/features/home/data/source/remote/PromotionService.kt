package com.example.lospibes.features.home.data.source.remote

import com.example.lospibes.features.home.data.dto.response.PromotionsResponse
import com.example.lospibes.utils.Constants.PROMOTIONS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PromotionService {
    @GET("${PROMOTIONS}/products")
    suspend fun getPromotions(
        @Query("day") day: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<PromotionsResponse>
}