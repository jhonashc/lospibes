package com.example.lospibes.common.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.domain.model.Product

@Composable
fun ProductList(
    products: List<Product>,
    favoriteProducts: List<Product> = listOf(),
    onNavigateToProductDetails: (productId: String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        itemsIndexed(products) { index, product ->
            Row(
                modifier = Modifier.padding(
                    start = if (index == 0) 20.dp else 0.dp,
                    end = if (product.id == products.last().id) 20.dp else 0.dp
                )
            )
            {
                ProductCard(
                    product = product,
                    isFavorite = favoriteProducts.contains(product),
                    onClick = {
                        onNavigateToProductDetails(product.id)
                    }
                )
            }
        }
    }
}