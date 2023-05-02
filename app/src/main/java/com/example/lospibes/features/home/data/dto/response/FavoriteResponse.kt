package com.example.lospibes.features.home.data.dto.response

import com.example.lospibes.features.home.domain.model.Product

data class FavoriteProductsResponse(
    val status: Boolean,
    val data: List<Product>
)

data class FavoriteProductResponse(
    val status: Boolean,
    val data: Product
)
