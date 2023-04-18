package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.core.model.ApiResponse
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(
        name: String? = null,
        category: String? = null
    ): Flow<NetworkResult<ApiResponse<List<Product>>>>

    fun getProductById(
        id: String
    ): Flow<NetworkResult<ApiResponse<Product>>>
}