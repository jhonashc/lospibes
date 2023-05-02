package com.example.lospibes.features.home.presentation.explore.presentation

import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
import com.example.lospibes.features.home.domain.model.Product

data class ExploreState(
    val message: String? = null,
    val isProductLoading: Boolean = true,
    val productList: List<Product> = emptyList(),
)

data class QueryState(
    val getProductsQueryDto: GetProductsQueryDto
)