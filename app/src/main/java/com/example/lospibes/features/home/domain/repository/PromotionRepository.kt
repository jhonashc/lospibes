package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.data.dto.query.GetPromotionsQueryDto
import com.example.lospibes.features.home.data.dto.response.PromotionsResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PromotionRepository {
    fun getPromotions(
        getPromotionsQueryDto: GetPromotionsQueryDto? = null
    ): Flow<NetworkResult<PromotionsResponse>>
}