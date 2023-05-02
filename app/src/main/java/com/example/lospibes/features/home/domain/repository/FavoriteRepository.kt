package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.data.dto.response.FavoriteProductResponse
import com.example.lospibes.features.home.data.dto.response.FavoriteProductsResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavoriteProducts(
        userId: String
    ): Flow<NetworkResult<FavoriteProductsResponse>>

    fun getFavoriteProduct(
        productId: String,
        userId: String
    ): Flow<NetworkResult<FavoriteProductResponse>>
}