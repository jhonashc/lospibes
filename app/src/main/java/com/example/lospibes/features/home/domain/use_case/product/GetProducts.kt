package com.example.lospibes.features.home.domain.use_case.product

import com.example.lospibes.core.model.ApiResponse
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.repository.ProductRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetProducts(
    private val productRepository: ProductRepository
) {
    operator fun invoke(
        name: String? = null,
        category: String? = null
    ): Flow<NetworkResult<ApiResponse<List<Product>>>> {
        return productRepository.getProducts(name, category)
    }
}