package com.example.lospibes.features.home.domain.use_case.product

import com.example.lospibes.features.home.data.dto.query.SearchProductsQueryDto
import com.example.lospibes.features.home.data.dto.response.ProductsResponse
import com.example.lospibes.features.home.domain.repository.ProductRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class SearchProducts(
    private val productRepository: ProductRepository
) {
    operator fun invoke(
        searchProductsQueryDto: SearchProductsQueryDto? = null
    ): Flow<NetworkResult<ProductsResponse>> {
        return productRepository.searchProducts(
            searchProductsQueryDto = searchProductsQueryDto
        )
    }
}