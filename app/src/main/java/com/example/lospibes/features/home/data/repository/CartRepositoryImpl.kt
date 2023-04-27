package com.example.lospibes.features.home.data.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.lospibes.features.home.domain.repository.CartRepository

class CartRepositoryImpl : CartRepository {
    private val _cartItemList = mutableStateListOf<String>()

    override fun addToCart(cartItemId: String): List<String> {
        _cartItemList.add(cartItemId)
        return _cartItemList
    }

    override fun removeFromCart(cartItemId: String): List<String> {
        _cartItemList.remove(cartItemId)
        return _cartItemList
    }

    override fun addQuantity(cartItemId: String): List<String> {
        TODO("Not yet implemented")
    }

    override fun subtractQuantity(cartItemId: String): List<String> {
        TODO("Not yet implemented")
    }

    override fun removeAll(): List<String> {
        _cartItemList.clear()
        return _cartItemList
    }
}