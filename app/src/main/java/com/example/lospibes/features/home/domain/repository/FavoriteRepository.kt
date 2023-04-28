package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.data.dto.response.FavoriteComboResponse
import com.example.lospibes.features.home.data.dto.response.FavoriteCombosResponse
import com.example.lospibes.features.home.data.dto.response.FavoriteProductResponse
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

    fun getFavoriteCombo(
        comboId: String,
        userId: String
    ): Flow<NetworkResult<FavoriteComboResponse>>

    fun getFavoriteProduct(
        productId: String,
        userId: String
    ): Flow<NetworkResult<FavoriteProductResponse>>
}