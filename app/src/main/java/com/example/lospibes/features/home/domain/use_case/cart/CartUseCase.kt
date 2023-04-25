package com.example.lospibes.features.home.domain.use_case.cart

data class CartUseCase(
    val addToCart: AddToCart,
    val removeFromCart: RemoveFromCart,
    val addQuantity: AddQuantity,
    val subtractQuantity: SubtractQuantity,
    val removeAll: RemoveAll
)
