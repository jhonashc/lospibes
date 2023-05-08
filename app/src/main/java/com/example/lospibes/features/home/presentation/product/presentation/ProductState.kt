package com.example.lospibes.features.home.presentation.product.presentation

import com.example.lospibes.features.home.domain.model.Product

data class ProductState(
    val message: String? = null,
    val isProductLoading: Boolean = true,
    val isFavoriteProductLoading: Boolean = true,
    val isSimilarProductsLoading: Boolean = true,
    val product: Product? = null,
    val favoriteProduct: Product? = null,
    val similarProductList: List<Product> = emptyList()
)
