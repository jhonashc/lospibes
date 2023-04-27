package com.example.lospibes.features.home.domain.repository

interface CartRepository {
    fun addToCart(cartItemId: String): List<String>

    fun removeFromCart(cartItemId: String): List<String>

    fun addQuantity(cartItemId: String): List<String>

    fun subtractQuantity(cartItemId: String): List<String>

    fun removeAll(): List<String>
}