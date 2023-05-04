package com.example.lospibes.features.home.presentation.filter.presentation

import com.example.lospibes.features.home.domain.model.Category

data class FilterState(
    val message: String? = null,
    val isCategoryLoading: Boolean = true,
    val categoryList: List<Category> = emptyList()
)
