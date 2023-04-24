package com.example.lospibes.features.home.viewmodel.cart

import com.example.lospibes.features.home.domain.model.CartItem

data class CartState(
    val cartItemList: List<CartItem> = emptyList()
)
