package com.example.lospibes.features.home.data.dto.response

import com.example.lospibes.features.home.domain.model.Product

data class ProductsResponse(
    val status: Boolean,
    val data: List<Product>
)

data class ProductResponse(
    val status: Boolean = true,
    val data: Product
)