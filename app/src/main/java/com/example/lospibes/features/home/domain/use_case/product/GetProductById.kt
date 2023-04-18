package com.example.lospibes.features.home.domain.use_case.product

import com.example.lospibes.core.model.ApiResponse
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.repository.ProductRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetProductById(
    private val productRepository: ProductRepository
) {
    operator fun invoke(
        id: String
    ): Flow<NetworkResult<ApiResponse<Product>>> {
        return productRepository.getProductById(id)
    }
}