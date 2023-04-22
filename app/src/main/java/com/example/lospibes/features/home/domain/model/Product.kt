package com.example.lospibes.features.home.domain.model

import java.util.Date

data class Product(
    val id: String,
    val name: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val price: Number,
    val stock: Number,
    val categories: List<Category> = emptyList(),
    val createdAt: Date,
    val updatedAt: Date
)
