package com.example.lospibes.features.home.data.repository

import com.example.lospibes.features.home.data.dto.query.GetPromotionsWithProductsQueryDto
import com.example.lospibes.features.home.data.dto.response.PromotionsWithProductsResponse
import com.example.lospibes.features.home.data.source.remote.PromotionService
import com.example.lospibes.features.home.domain.repository.PromotionRepository
import com.example.lospibes.utils.BaseApiResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PromotionRepositoryImpl @Inject constructor(
    private val promotionService: PromotionService
) : PromotionRepository, BaseApiResponse() {
    override fun getPromotionsWithProducts(
        getPromotionsWithProductsQueryDto: GetPromotionsWithProductsQueryDto?
    ): Flow<NetworkResult<PromotionsWithProductsResponse>> {
        return safeApiCall {
            promotionService.getPromotionsWithProducts(
                day = getPromotionsWithProductsQueryDto?.day,
                limit = getPromotionsWithProductsQueryDto?.limit,
                offset = getPromotionsWithProductsQueryDto?.offset
            )
        }
    }
}