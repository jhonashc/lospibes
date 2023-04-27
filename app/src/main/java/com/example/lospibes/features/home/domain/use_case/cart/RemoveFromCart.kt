package com.example.lospibes.features.home.domain.use_case.cart

import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.repository.CartRepository

class RemoveFromCart(
    private val cartRepository: CartRepository
) {
    operator fun invoke(
        cartItem: CartItem
    ): List<CartItem> {
        return cartRepository.removeFromCart(cartItem)
    }
}