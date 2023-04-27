package com.example.lospibes.features.home.domain.model

data class CartItem(
    val id: String,
    val name: String,
    val imageUrl: String? = null,
    val price: Number,
    val quantity: Int,
    val isCombo: Boolean
)
