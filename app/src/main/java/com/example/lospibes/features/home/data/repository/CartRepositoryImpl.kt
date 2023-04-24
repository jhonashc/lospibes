package com.example.lospibes.features.home.data.repository

import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.repository.CartRepository

class CartRepositoryImpl : CartRepository {
    private val cartList = mutableListOf<CartItem>()

    override fun addToCart(cartItem: CartItem): List<CartItem> {
        cartList.add(cartItem)
        return cartList
    }
}