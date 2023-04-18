package com.example.lospibes.features.home.presentation.home.presentation

import com.example.lospibes.features.home.domain.model.Product

data class HomeState(
    val productList: List<Product> = emptyList(),
    val isLoading: Boolean = true,
    val message: Any? = null
)
