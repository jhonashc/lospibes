package com.example.lospibes.features.home.view_model.cart

import com.example.lospibes.features.home.domain.model.CartItem

data class CartState(
    val cartItemList: List<CartItem> = emptyList()
)
