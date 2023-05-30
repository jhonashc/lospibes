package com.example.lospibes.features.home.presentation.explore.presentation

import com.example.lospibes.features.home.domain.model.Category

data class ExploreBottomState(
    val status: Boolean = false,
    val message: String? = null,
    val isLoading: Boolean = true,
    val category: String = "",
    val range: ClosedFloatingPointRange<Float> = 1f..20f,
    val valueRange: ClosedFloatingPointRange<Float> = 1f..20f,
    val categoryList: List<Category> = emptyList()
)
