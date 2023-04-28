package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.data.dto.response.FavoriteCombosResponse
import com.example.lospibes.features.home.data.dto.response.FavoriteProductsResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavoriteCombos(
        userId: String
    ): Flow<NetworkResult<FavoriteCombosResponse>>

    fun getFavoriteProducts(
        userId: String
    ): Flow<NetworkResult<FavoriteProductsResponse>>
}