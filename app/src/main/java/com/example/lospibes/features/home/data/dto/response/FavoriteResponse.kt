package com.example.lospibes.features.home.data.dto.response

import com.example.lospibes.features.home.domain.model.Product

data class FavoriteProductsResponse(
    val data: List<Product>
)

data class FavoriteProductResponse(
    val data: Product
)
