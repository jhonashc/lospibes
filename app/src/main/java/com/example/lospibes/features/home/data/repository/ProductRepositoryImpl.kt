package com.example.lospibes.features.home.data.repository

import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
import com.example.lospibes.features.home.data.dto.query.GetSimilarProductsQueryDto
import com.example.lospibes.features.home.data.dto.query.SearchProductsQueryDto
import com.example.lospibes.features.home.data.dto.response.ProductResponse
import com.example.lospibes.features.home.data.dto.response.ProductsResponse
import com.example.lospibes.features.home.data.source.remote.ProductService
import com.example.lospibes.features.home.domain.repository.ProductRepository
import com.example.lospibes.utils.BaseApiResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository, BaseApiResponse() {
    override fun getProducts(
        getProductsQueryDto: GetProductsQueryDto?
    ): Flow<NetworkResult<ProductsResponse>> {
        return safeApiCall {
            productService.getProducts(
                limit = getProductsQueryDto?.limit,
                offset = getProductsQueryDto?.offset
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getSimilarProducts(
        id: String,
        getSimilarProductsQueryDto: GetSimilarProductsQueryDto?
    ): Flow<NetworkResult<ProductsResponse>> {
        return safeApiCall {
            productService.getSimilarProducts(
                id = id,
                limit = getSimilarProductsQueryDto?.limit,
                offset = getSimilarProductsQueryDto?.offset
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getProductById(
        id: String
    ): Flow<NetworkResult<ProductResponse>> {
        return safeApiCall {
            productService.getProductById(
                id = id
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun searchProducts(
        searchProductsQueryDto: SearchProductsQueryDto?
    ): Flow<NetworkResult<ProductsResponse>> {
        return safeApiCall {
            productService.searchProducts(
                name = searchProductsQueryDto?.name,
                category = searchProductsQueryDto?.category,
                min = searchProductsQueryDto?.min,
                max = searchProductsQueryDto?.max,
                limit = searchProductsQueryDto?.limit,
                offset = searchProductsQueryDto?.offset
            )
        }.flowOn(Dispatchers.IO)
    }
}