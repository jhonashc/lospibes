package com.example.lospibes.features.home.data.repository

import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
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
                name = getProductsQueryDto?.name,
                category = getProductsQueryDto?.category,
                min = getProductsQueryDto?.min,
                max = getProductsQueryDto?.max,
                limit = getProductsQueryDto?.limit,
                offset = getProductsQueryDto?.offset
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getProductById(
        id: String
    ): Flow<NetworkResult<ProductResponse>> {
        return safeApiCall {
            productService.getProductById(id)
        }.flowOn(Dispatchers.IO)
    }
}