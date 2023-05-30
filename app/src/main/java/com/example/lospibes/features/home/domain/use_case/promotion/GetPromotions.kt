package com.example.lospibes.features.home.domain.use_case.promotion

import com.example.lospibes.features.home.data.dto.query.GetPromotionsQueryDto
import com.example.lospibes.features.home.data.dto.response.PromotionsResponse
import com.example.lospibes.features.home.domain.repository.PromotionRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetPromotions(
    private val promotionRepository: PromotionRepository
) {
    operator fun invoke(
        getPromotionsQueryDto: GetPromotionsQueryDto? = null
    ): Flow<NetworkResult<PromotionsResponse>> {
        return promotionRepository.getPromotions(
            getPromotionsQueryDto = getPromotionsQueryDto
        )
    }
}