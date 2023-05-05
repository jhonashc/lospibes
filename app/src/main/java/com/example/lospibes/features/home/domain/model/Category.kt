package com.example.lospibes.features.home.domain.model

data class Category(
    val id: String,
    val name: String
)

fun Category.toTabItem() = TabItem(
    name = name
)
