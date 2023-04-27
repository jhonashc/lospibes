package com.example.lospibes.features.home.domain.use_case.favorite

import com.example.lospibes.features.home.data.dto.response.FavoriteProductResponse
import com.example.lospibes.features.home.domain.repository.FavoriteRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetFavoriteProducts(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(
        userId: String
    ): Flow<NetworkResult<FavoriteProductResponse>> {
        return favoriteRepository.getFavoriteProducts(userId)
    }
}