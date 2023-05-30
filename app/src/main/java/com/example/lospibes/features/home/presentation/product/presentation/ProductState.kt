package com.example.lospibes.features.home.presentation.product.presentation

import com.example.lospibes.features.home.domain.model.Product

data class ProductState(
    val status: Boolean = false,
    val message: String? = null,
    val isLoading: Boolean = true,
    val product: Product? = null,
    val favoriteProduct: Product? = null,
    val similarProductList: List<Product> = emptyList()
)
