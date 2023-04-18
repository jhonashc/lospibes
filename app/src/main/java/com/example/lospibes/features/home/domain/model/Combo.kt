package com.example.lospibes.features.home.domain.model

data class Combo(
    val id: String,
    val name: String,
    val description: String? = null,
    val price: Number,
    val imageUrl: String? = null,
    val products: List<Product> = emptyList()
)
