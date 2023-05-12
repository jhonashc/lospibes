package com.example.lospibes.features.home.domain.use_case.promotion

import com.example.lospibes.features.home.data.dto.query.GetPromotionsWithProductsQueryDto
import com.example.lospibes.features.home.data.dto.response.PromotionsWithProductsResponse
import com.example.lospibes.features.home.domain.repository.PromotionRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetPromotionsWithProducts(
    private val promotionRepository: PromotionRepository
) {
    operator fun invoke(
        getPromotionsWithProductsQueryDto: GetPromotionsWithProductsQueryDto? = null
    ): Flow<NetworkResult<PromotionsWithProductsResponse>> {
        return promotionRepository.getPromotionsWithProducts(
            getPromotionsWithProductsQueryDto = getPromotionsWithProductsQueryDto
        )
    }
}