package com.example.lospibes.features.home.domain.use_case.product

import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
import com.example.lospibes.features.home.data.dto.response.ProductsResponse
import com.example.lospibes.features.home.domain.repository.ProductRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetProducts(
    private val productRepository: ProductRepository
) {
    operator fun invoke(
        getProductsQueryDto: GetProductsQueryDto? = null
    ): Flow<NetworkResult<ProductsResponse>> {
        return productRepository.getProducts(getProductsQueryDto)
    }
}