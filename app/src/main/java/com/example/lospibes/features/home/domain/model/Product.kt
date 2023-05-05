package com.example.lospibes.features.home.domain.model

data class Product(
    val id: String,
    val name: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val price: Number,
    val stock: Int,
    val categories: List<Category> = emptyList()
)

fun Product.toCardItem() = CardItem(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    price = price
)

fun Product.toCartItem() = CartItem(
    id = id,
    name = name,
    imageUrl = imageUrl,
    price = price,
    quantity = 1
)