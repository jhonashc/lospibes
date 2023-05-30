package com.example.lospibes.features.home.data.repository

import com.example.lospibes.features.home.data.dto.response.FavoriteProductResponse
import com.example.lospibes.features.home.data.dto.response.FavoriteProductsResponse
import com.example.lospibes.features.home.data.source.remote.FavoriteService
import com.example.lospibes.features.home.domain.repository.FavoriteRepository
import com.example.lospibes.utils.BaseApiResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteService: FavoriteService
) : FavoriteRepository, BaseApiResponse() {
    override fun getFavoriteProducts(
        userId: String
    ): Flow<NetworkResult<FavoriteProductsResponse>> {
        return safeApiCall {
            favoriteService.getFavoriteProducts(
                userId = userId
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getFavoriteProduct(
        userId: String,
        productId: String,
    ): Flow<NetworkResult<FavoriteProductResponse>> {
        return safeApiCall {
            favoriteService.getFavoriteProduct(
                userId = userId,
                productId = productId
            )
        }.flowOn(Dispatchers.IO)
    }
}