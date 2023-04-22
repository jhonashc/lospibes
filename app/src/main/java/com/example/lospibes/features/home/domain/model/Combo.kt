package com.example.lospibes.features.home.domain.model

import java.util.Date

data class Combo(
    val id: String,
    val name: String,
    val description: String? = null,
    val price: Number,
    val imageUrl: String? = null,
    val products: List<ComboProduct>,
    val createdAt: Date,
    val updatedAt: Date
)

data class ComboProduct(
    val product: Product,
    val quantity: Int
)