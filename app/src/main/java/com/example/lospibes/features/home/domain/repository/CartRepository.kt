package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.domain.model.CartItem

interface CartRepository {
    fun addToCart(cartItem: CartItem): List<CartItem>
}