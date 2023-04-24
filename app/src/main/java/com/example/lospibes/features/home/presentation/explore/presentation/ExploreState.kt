package com.example.lospibes.features.home.presentation.explore.presentation

import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
import com.example.lospibes.features.home.domain.model.Category
import com.example.lospibes.features.home.domain.model.Combo
import com.example.lospibes.features.home.domain.model.Product

data class ExploreState(
    val message: String? = null,
    val categoryList: List<Category> = emptyList(),
    val comboList: List<Combo> = emptyList(),
    val productList: List<Product> = emptyList(),
    val isCategoryLoading: Boolean = true,
    val isComboLoading: Boolean = true,
    val isProductLoading: Boolean = true
)

data class QueryState(
    val getProductsQueryDto: GetProductsQueryDto
)