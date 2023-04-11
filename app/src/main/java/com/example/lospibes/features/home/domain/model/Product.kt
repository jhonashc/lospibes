package com.example.lospibes.features.home.domain.model

data class Product(
    val id: String,
    val name: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val price: Number,
    val stock: Int,
    val categories: List<Category>
)
