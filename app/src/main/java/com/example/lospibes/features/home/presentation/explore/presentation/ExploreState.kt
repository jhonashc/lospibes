package com.example.lospibes.features.home.presentation.explore.presentation

import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.SearchWidgetState

data class ExploreState(
    val message: String? = null,
    val isLoading: Boolean = true,
    val searchText: String = "",
    val searchWidgetState: SearchWidgetState = SearchWidgetState.CLOSED,
    val productList: List<Product> = emptyList(),
)

data class QueryState(
    val getProductsQueryDto: GetProductsQueryDto
)