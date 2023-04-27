package com.example.lospibes.features.home.domain.model

data class CardItem(
    val id: String,
    val name: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val price: Number,
    val isCombo: Boolean,
)

fun CardItem.toCartItem() = CartItem(
    id = id,
    name = name,
    imageUrl = imageUrl,
    price = price,
    quantity = 1,
    isCombo = isCombo,
)