package com.example.lospibes.features.home.data.repository

import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.repository.CartRepository

class CartRepositoryImpl : CartRepository {
    private val cartList: MutableList<CartItem> = mutableListOf()

    override fun addToCart(cartItem: CartItem): List<CartItem> {
        cartList.add(cartItem)
        return cartList
    }

    override fun removeFromCart(cartItem: CartItem): List<CartItem> {
        return cartList.filter { item -> item.id != cartItem.id }
    }

    override fun addQuantity(cartItem: CartItem): List<CartItem> {
        return cartList.map { item ->
            if (item.id == cartItem.id) {
                return@map CartItem(
                    id = item.id,
                    name = item.name,
                    imageUrl = item.imageUrl,
                    price = item.price,
                    quantity = item.quantity + 1
                )
            }
            return@map item
        }
    }

    override fun subtractQuantity(cartItem: CartItem): List<CartItem> {
        return cartList.map { item ->
            if (item.id == cartItem.id) {
                return@map CartItem(
                    id = item.id,
                    name = item.name,
                    imageUrl = item.imageUrl,
                    price = item.price,
                    quantity = item.quantity - 1
                )
            }
            return@map item
        }.filter { item -> item.quantity > 0 }
    }

    override fun removeAll(): List<CartItem> {
        cartList.clear()
        return cartList
    }
}