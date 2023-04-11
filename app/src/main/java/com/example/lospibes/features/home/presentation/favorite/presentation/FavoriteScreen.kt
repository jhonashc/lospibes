package com.example.lospibes.features.home.presentation.favorite.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lospibes.core.components.ProductListGrid
import com.example.lospibes.core.components.StandardScaffold
import com.example.lospibes.core.components.StandardTopAppBar
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
        }
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
    ProductListGrid(
        products = products,
        favoriteProducts = products.subList(0, 2),
        onSelectProduct = { selectedProduct ->
            onNavigateToProductDetails(selectedProduct.id)
        }
    )
}