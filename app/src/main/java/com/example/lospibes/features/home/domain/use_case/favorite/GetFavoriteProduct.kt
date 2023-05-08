package com.example.lospibes.features.home.domain.use_case.favorite

import com.example.lospibes.features.home.data.dto.response.FavoriteProductResponse
import com.example.lospibes.features.home.domain.repository.FavoriteRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetFavoriteProduct(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(
        userId: String,
        productId: String
    ): Flow<NetworkResult<FavoriteProductResponse>> {
        return favoriteRepository.getFavoriteProduct(
            userId = userId,
            productId = productId
        )
    }
}