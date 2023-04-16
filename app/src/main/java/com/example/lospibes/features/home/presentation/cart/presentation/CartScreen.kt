package com.example.lospibes.features.home.presentation.cart.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.lospibes.core.components.StandardTopBar
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.presentation.cart.component.CartList
import com.example.lospibes.utils.Constants.products

@Composable
fun CartScreen(
    onNavigateToHome: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier.weight(0.3f, true)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Header(
                    onNavigateToHome = onNavigateToHome
                )
            }
        }
        Row(
            modifier = Modifier.weight(2f, true)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Body()
            }
        }
        Row(
            modifier = Modifier.weight(1f, true)
        ) {
            Footer()
        }
    }
}

@Composable
private fun Header(
    onNavigateToHome: () -> Unit
) {
    StandardTopBar(
        title = "Cart",
        onBackTo = onNavigateToHome
    )
}

@Composable
private fun Body() {
    CartListSection()
}

@Composable
private fun CartListSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        CartList(cartItemList = products.subList(0, 2).map { product ->
            CartItem(
                id = product.id,
                name = product.name,
                imageUrl = product.imageUrl,
                price = product.price,
                quantity = 1
            )
        })
    }
}

@Composable
private fun TotalSection() {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
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
                    text = "$ 365",
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Descuento",
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Text(
                    text = "-$ 26",
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total",
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Text(
                    text = "-$ 339",
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
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
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
private fun Footer() {
    TotalSection()
}