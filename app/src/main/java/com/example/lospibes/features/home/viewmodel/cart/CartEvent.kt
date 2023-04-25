package com.example.lospibes.features.home.viewmodel.cart

import com.example.lospibes.features.home.domain.model.CartItem

sealed class CartEvent {
    data class AddToCart(val cartItem: CartItem) : CartEvent()
    data class RemoveFromCart(val cartItem: CartItem) : CartEvent()
    data class AddQuantity(val cartItem: CartItem) : CartEvent()
    data class SubtractQuantity(val cartItem: CartItem) : CartEvent()
    object RemoveAll : CartEvent()
}
