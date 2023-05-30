package com.example.lospibes.features.home.presentation.favorite.presentation

import com.example.lospibes.features.home.domain.model.Product

data class FavoriteState(
    val message: String? = null,
    val isLoading: Boolean = true,
    val favoriteProductList: List<Product> = emptyList()
)
