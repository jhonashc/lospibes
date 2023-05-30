package com.example.lospibes.features.home.data.repository

import com.example.lospibes.features.home.data.dto.query.GetPromotionsQueryDto
import com.example.lospibes.features.home.data.dto.response.PromotionsResponse
import com.example.lospibes.features.home.data.source.remote.PromotionService
import com.example.lospibes.features.home.domain.repository.PromotionRepository
import com.example.lospibes.utils.BaseApiResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PromotionRepositoryImpl @Inject constructor(
    private val promotionService: PromotionService
) : PromotionRepository, BaseApiResponse() {
    override fun getPromotions(
        getPromotionsQueryDto: GetPromotionsQueryDto?
    ): Flow<NetworkResult<PromotionsResponse>> {
        return safeApiCall {
            promotionService.getPromotions(
                day = getPromotionsQueryDto?.day,
                limit = getPromotionsQueryDto?.limit,
                offset = getPromotionsQueryDto?.offset
            )
        }.flowOn(Dispatchers.IO)
    }
}