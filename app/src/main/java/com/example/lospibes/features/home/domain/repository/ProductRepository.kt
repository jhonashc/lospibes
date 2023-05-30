package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
import com.example.lospibes.features.home.data.dto.query.GetSimilarProductsQueryDto
import com.example.lospibes.features.home.data.dto.query.SearchProductsQueryDto
import com.example.lospibes.features.home.data.dto.response.ProductResponse
import com.example.lospibes.features.home.data.dto.response.ProductsResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(
        getProductsQueryDto: GetProductsQueryDto? = null
    ): Flow<NetworkResult<ProductsResponse>>

    fun getSimilarProducts(
        id: String,
        getSimilarProductsQueryDto: GetSimilarProductsQueryDto? = null
    ): Flow<NetworkResult<ProductsResponse>>

    fun getProductById(
        id: String
    ): Flow<NetworkResult<ProductResponse>>

    fun searchProducts(
        searchProductsQueryDto: SearchProductsQueryDto? = null
    ): Flow<NetworkResult<ProductsResponse>>
}