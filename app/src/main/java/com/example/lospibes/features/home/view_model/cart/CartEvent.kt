package com.example.lospibes.features.home.view_model.cart

import com.example.lospibes.features.home.domain.model.CartItem

sealed class CartEvent {
    data class AddToCart(val value: CartItem) : CartEvent()
    data class RemoveFromCart(val value: CartItem) : CartEvent()
    data class AddQuantity(val value: CartItem) : CartEvent()
    data class SubtractQuantity(val value: CartItem) : CartEvent()
    object RemoveAll : CartEvent()
}
