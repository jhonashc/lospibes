package com.example.lospibes.features.home.presentation.favorite.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.core.component.SearchTopBar
import com.example.lospibes.core.component.StandardTopBar
import com.example.lospibes.features.home.component.ProductListGrid
import com.example.lospibes.utils.Constants.products

@Composable
fun FavoriteScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(
            onNavigateToHome = onNavigateToHome
        )

        Body(
            onNavigateToDetails = onNavigateToDetails
        )
    }
}

@Composable
private fun Header(
    onNavigateToHome: () -> Unit
) {
    var value by remember { mutableStateOf("") }
    val onValueChange = { newValue: String -> value = newValue }

    var isVisibleSearchBar by remember { mutableStateOf(false) }

    if (!isVisibleSearchBar) {
        StandardTopBar(
            title = "Favoritos",
            navigationIcon = {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back Icon"
                )
            },
            actions = {
                IconButton(
                    modifier = Modifier.padding(end = 5.dp),
                    onClick = { isVisibleSearchBar = true }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon"
                    )
                }
            },
            onBackTo = onNavigateToHome
        )
    } else {
        SearchTopBar(
            value = value,
            onSubmit = {},
            onClose = {
                value = ""
                isVisibleSearchBar = false
            },
            onValueChange = onValueChange
        )
    }
}

@Composable
private fun Body(
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    FavoriteSection(
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
fun FavoriteSection(
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    ProductListGrid(
        products = products,
        favoriteProducts = products,
        onProductSelected = { selectedProduct ->
            onNavigateToDetails(false, selectedProduct.id)
        }
    )
}