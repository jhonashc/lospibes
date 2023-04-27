package com.example.lospibes.features.home.domain.use_case.cart

import com.example.lospibes.features.home.domain.repository.CartRepository

class SubtractQuantity(
    private val cartRepository: CartRepository
) {
    operator fun invoke(
        cartItemId: String
    ): List<String> {
        return cartRepository.subtractQuantity(cartItemId)
    }
}