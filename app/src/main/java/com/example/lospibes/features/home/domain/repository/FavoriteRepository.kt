package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.data.dto.response.FavoriteComboResponse
import com.example.lospibes.features.home.data.dto.response.FavoriteProductResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavoriteCombos(
        userId: String
    ): Flow<NetworkResult<FavoriteComboResponse>>

    fun getFavoriteProducts(
        userId: String
    ): Flow<NetworkResult<FavoriteProductResponse>>
}