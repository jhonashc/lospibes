package com.example.lospibes.features.home.presentation.explore.presentation

import com.example.lospibes.features.home.data.dto.query.SearchProductsQueryDto
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.SearchWidgetState

data class ExploreState(
    val status: Boolean = false,
    val message: String? = null,
    val isLoading: Boolean = true,
    val isOpenBottomSheet: Boolean = false,
    val query: String = "",
    val isActive: Boolean = false,
    val searchWidgetState: SearchWidgetState = SearchWidgetState.CLOSED,
    val productList: List<Product> = emptyList(),
    val searchProductList: List<Product> = emptyList(),
    val searchProductsQueryDto: SearchProductsQueryDto? = null
)