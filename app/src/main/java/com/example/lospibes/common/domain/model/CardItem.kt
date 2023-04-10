package com.example.lospibes.common.domain.model

data class CardItem(
    val name: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val price: Number
)
