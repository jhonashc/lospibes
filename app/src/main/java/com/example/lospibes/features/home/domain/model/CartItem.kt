package com.example.lospibes.features.home.domain.model

data class CartItem(
    val id: String,
    val name: String,
    val imageUrl: String? = null,
    val price: Double,
    val quantity: Int
)
