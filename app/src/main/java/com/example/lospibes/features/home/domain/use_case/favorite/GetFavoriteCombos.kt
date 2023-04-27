package com.example.lospibes.features.home.domain.use_case.favorite

import com.example.lospibes.features.home.data.dto.response.FavoriteComboResponse
import com.example.lospibes.features.home.domain.repository.FavoriteRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetFavoriteCombos(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(
        userId: String
    ): Flow<NetworkResult<FavoriteComboResponse>> {
        return favoriteRepository.getFavoriteCombos(userId)
    }
}