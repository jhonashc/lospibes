package com.example.lospibes.features.home.presentation.cart.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.view_model.cart.CartEvent
import com.example.lospibes.features.home.view_model.cart.CartViewModel

@Composable
fun CartList(
    cartViewModel: CartViewModel
) {
    val cartState = cartViewModel.state.collectAsState()

    val cartItemList: List<CartItem> = cartState.value.cartItemList

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        cartItemList.forEach { cartItem ->
            CartListItem(
                cartItem = cartItem,
                onAddQuantityClick = {
                    cartViewModel.onEvent(CartEvent.AddQuantity(cartItem))
                },
                onSubtractQuantityClick = {
                    cartViewModel.onEvent(CartEvent.SubtractQuantity(cartItem))
                }
            )
        }
    }
}