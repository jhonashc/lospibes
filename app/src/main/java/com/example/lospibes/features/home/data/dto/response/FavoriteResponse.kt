package com.example.lospibes.features.home.data.dto.response

import com.example.lospibes.features.home.domain.model.Combo
import com.example.lospibes.features.home.domain.model.Product

data class FavoriteComboResponse(
    val status: Boolean,
    val data: List<Combo>
)

data class FavoriteProductResponse(
    val status: Boolean,
    val data: List<Product>
)
