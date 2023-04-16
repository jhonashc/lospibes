package com.example.lospibes.features.home.presentation.cart.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.features.home.domain.model.CartItem

@Composable
fun CartList(
    cartItemList: List<CartItem>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        cartItemList.forEach { cartItem ->
            CartListItem(
                cartItem = cartItem
            )
        }
    }
}