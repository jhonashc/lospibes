package com.example.lospibes.features.home.data.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.repository.CartRepository

class CartRepositoryImpl : CartRepository {
    private val _cartItemList = mutableStateListOf<CartItem>()

    override fun addToCart(cartItem: CartItem): List<CartItem> {
        _cartItemList.add(cartItem)
        return _cartItemList
    }

    override fun removeFromCart(cartItem: CartItem): List<CartItem> {
        _cartItemList.remove(cartItem)
        return _cartItemList
    }

    override fun addQuantity(cartItem: CartItem): List<CartItem> {
        TODO("Not yet implemented")
    }

    override fun subtractQuantity(cartItem: CartItem): List<CartItem> {
        TODO("Not yet implemented")
    }

    override fun removeAll(): List<CartItem> {
        _cartItemList.clear()
        return _cartItemList
    }
}