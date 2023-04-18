package com.example.lospibes.features.home.data.repository

import com.example.lospibes.core.model.ApiResponse
import com.example.lospibes.features.home.data.source.remote.ProductService
import com.example.lospibes.features.home.domain.model.Product
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
        name: String?,
        category: String?
    ): Flow<NetworkResult<ApiResponse<List<Product>>>> {
        return safeApiCall {
            productService.getProducts(name, category)
        }.flowOn(Dispatchers.IO)
    }

    override fun getProductById(
        id: String
    ): Flow<NetworkResult<ApiResponse<Product>>> {
        return safeApiCall {
            productService.getProductById(id)
        }.flowOn(Dispatchers.IO)
    }
}