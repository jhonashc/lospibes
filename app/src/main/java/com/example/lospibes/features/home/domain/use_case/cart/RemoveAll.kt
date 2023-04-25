package com.example.lospibes.features.home.domain.use_case.cart

import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.repository.CartRepository

class RemoveAll(
    private val cartRepository: CartRepository
) {
    operator fun invoke(): List<CartItem> {
        return cartRepository.removeAll()
    }
}