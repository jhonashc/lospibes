package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.domain.model.CartItem

interface CartRepository {
    fun addToCart(cartItem: CartItem): List<CartItem>

    fun removeFromCart(cartItem: CartItem): List<CartItem>

    fun addQuantity(cartItem: CartItem): List<CartItem>

    fun subtractQuantity(cartItem: CartItem): List<CartItem>

    fun removeAll(): List<CartItem>
}