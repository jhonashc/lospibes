package com.example.lospibes.features.home.favorite.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.components.ProductCard
import com.example.lospibes.common.components.StandardScaffold
import com.example.lospibes.common.components.StandardTopAppBar
import com.example.lospibes.utils.Constants.products

@Composable
fun FavoriteScreen(
    onNavigateToProductDetails: (productId: String) -> Unit
) {
    StandardScaffold(
        topAppBar = {
            StandardTopAppBar(
                title = "Favoritos"
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Content(
                onNavigateToProductDetails = onNavigateToProductDetails
            )
        }
    }
}

@Composable
private fun Content(
    onNavigateToProductDetails: (productId: String) -> Unit
) {
    ProductFavoriteList(
        onNavigateToProductDetails = onNavigateToProductDetails
    )
}

@Composable
private fun ProductFavoriteList(
    onNavigateToProductDetails: (productId: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(20.dp)
    ) {
        items(products) { product ->
            ProductCard(
                product = product,
                isFavorite = true,
                onClick = {
                    onNavigateToProductDetails(product.id)
                }
            )
        }
    }
}