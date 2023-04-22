package com.example.lospibes.features.home.domain.model

import java.util.Date

data class Combo(
    val id: String,
    val name: String,
    val description: String? = null,
    val price: Number,
    val imageUrl: String? = null,
    val products: List<Product>,
    val createdAt: Date,
    val updatedAt: Date
)
