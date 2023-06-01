package com.example.lospibes.features.home.presentation.cart.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.lospibes.core.component.StandardScaffold
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.presentation.cart.component.CartList
import com.example.lospibes.features.home.presentation.cart.component.CartTopBar
import com.example.lospibes.features.home.presentation.cart.component.EmptyCart
import com.example.lospibes.features.home.view_model.cart.CartViewModel
import com.example.lospibes.utils.formatNumber

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    onNavigateToHome: () -> Unit
) {
    val cartState = cartViewModel.state.collectAsState()

    val cartListItem: List<CartItem> = cartState.value.cartItemList

    StandardScaffold(
        topAppBar = {
            Header(
                onNavigateToHome = onNavigateToHome
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                if (cartListItem.isEmpty()) {
                    EmptyCart()
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Body(
                            cartViewModel = cartViewModel
                        )
                    }
                }
            }

            if (cartListItem.isNotEmpty()) {
                Footer(
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}

@Composable
private fun Header(
    onNavigateToHome: () -> Unit
) {
    CartTopBar(
        onNavigateToHome = onNavigateToHome
    )
}

@Composable
private fun Body(
    cartViewModel: CartViewModel
) {
    CartListSection(
        cartViewModel = cartViewModel
    )
}

@Composable
private fun CartListSection(
    cartViewModel: CartViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        CartList(
            cartViewModel = cartViewModel
        )
    }
}

@Composable
private fun Footer(
    cartViewModel: CartViewModel
) {
    TotalSection(
        cartViewModel = cartViewModel
    )
}

@Composable
private fun TotalSection(
    cartViewModel: CartViewModel
) {
    val cartState = cartViewModel.state.collectAsState()

    val cartItemList: List<CartItem> = cartState.value.cartItemList

    val subTotal = cartItemList.fold(0.0) { acc, cartItem ->
        acc + (cartItem.price * cartItem.quantity)
    }

    val total = subTotal + (subTotal * 0.12f)

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Subtotal",
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Text(
                    text = "$ ${formatNumber(subTotal)}",
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            Divider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total",
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Text(
                    text = "$ ${formatNumber(total)}",
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = MaterialTheme.shapes.extraSmall,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Siguiente",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}