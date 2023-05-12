package com.example.lospibes.features.home.presentation.home.presentation

import com.example.lospibes.features.home.domain.model.Category
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.PromotionWithProducts

data class HomeState(
    val message: String? = null,
    val isLoading: Boolean = true,
    val categoryList: List<Category> = emptyList(),
    val productList: List<Product> = emptyList(),
    val promotionList: List<PromotionWithProducts> = emptyList()
)
