package com.example.lospibes.features.home.data.dto.response

import com.example.lospibes.features.home.domain.model.Product

data class ProductsResponse(
    val data: List<Product>
)

data class ProductResponse(
    val data: Product
)