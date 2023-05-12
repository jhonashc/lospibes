package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.data.dto.query.GetPromotionsWithProductsQueryDto
import com.example.lospibes.features.home.data.dto.response.PromotionsWithProductsResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PromotionRepository {
    fun getPromotionsWithProducts(
        getPromotionsWithProductsQueryDto: GetPromotionsWithProductsQueryDto? = null
    ): Flow<NetworkResult<PromotionsWithProductsResponse>>
}