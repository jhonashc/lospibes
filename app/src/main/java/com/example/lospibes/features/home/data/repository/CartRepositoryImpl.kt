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
        val index = _cartItemList.indexOfFirst { it.id == cartItem.id }

        if (index != -1) {
            _cartItemList.removeAt(index)
        }

        return _cartItemList
    }

    override fun addQuantity(cartItem: CartItem): List<CartItem> {
        val index = _cartItemList.indexOfFirst { it.id == cartItem.id }

        if (index != -1) {
            val newCartItem = _cartItemList[index].copy(
                quantity = cartItem.quantity + 1
            )

            _cartItemList[index] = newCartItem
        }

        return _cartItemList
    }

    override fun subtractQuantity(cartItem: CartItem): List<CartItem> {
        val index = _cartItemList.indexOfFirst { it.id == cartItem.id }

        if (index != -1) {
            val newCartItem = _cartItemList[index].copy(
                quantity = cartItem.quantity - 1
            )

            if (newCartItem.quantity != 0) {
                _cartItemList[index] = newCartItem
            } else {
                _cartItemList.remove(cartItem)
            }

        }

        return _cartItemList
    }

    override fun removeAll(): List<CartItem> {
        _cartItemList.clear()
        return _cartItemList
    }
}