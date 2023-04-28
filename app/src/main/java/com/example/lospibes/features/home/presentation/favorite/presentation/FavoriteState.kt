package com.example.lospibes.features.home.presentation.favorite.presentation

import com.example.lospibes.features.home.domain.model.Combo
import com.example.lospibes.features.home.domain.model.Product

data class FavoriteState(
    val message: String? = null,
    val isFavoriteComboLoading: Boolean = true,
    val isFavoriteProductLoading: Boolean = true,
    val favoriteComboList: List<Combo> = emptyList(),
    val favoriteProductList: List<Product> = emptyList(),
)
