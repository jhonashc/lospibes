package com.example.lospibes.features.home.viewmodel.cart

sealed class CartEvent {
    data class AddToCart(val value: String) : CartEvent()
    data class RemoveFromCart(val value: String) : CartEvent()
    data class AddQuantity(val value: String) : CartEvent()
    data class SubtractQuantity(val value: String) : CartEvent()
    object RemoveAll : CartEvent()
}
