package com.example.lospibes.features.home.presentation.favorite.presentation

import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.SearchWidgetState

data class FavoriteState(
    val message: String? = null,
    val isLoading: Boolean = true,
    val searchText: String = "",
    val searchWidgetState: SearchWidgetState = SearchWidgetState.CLOSED,
    val favoriteProductList: List<Product> = emptyList()
)
