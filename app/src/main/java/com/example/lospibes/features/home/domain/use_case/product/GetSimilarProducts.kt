package com.example.lospibes.features.home.domain.use_case.product

import com.example.lospibes.features.home.data.dto.query.GetSimilarProductsQueryDto
import com.example.lospibes.features.home.data.dto.response.ProductsResponse
import com.example.lospibes.features.home.domain.repository.ProductRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetSimilarProducts(
    private val productRepository: ProductRepository
) {
    operator fun invoke(
        id: String,
        getSimilarProductsQueryDto: GetSimilarProductsQueryDto? = null
    ): Flow<NetworkResult<ProductsResponse>> {
        return productRepository.getSimilarProducts(id, getSimilarProductsQueryDto)
    }
}