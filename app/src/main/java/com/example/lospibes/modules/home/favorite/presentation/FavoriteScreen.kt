package com.example.lospibes.modules.home.favorite.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.components.ProductCard
import com.example.lospibes.utils.Constants.products

@Composable
fun FavoriteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Content()
    }
}

@Composable
fun Content() {
    Title()
    Spacer(modifier = Modifier.height(26.dp))
    FavoriteList()
}

@Composable
fun Title() {
    Text(
        text = "Favoritos",
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
fun FavoriteList() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products) { product ->
            ProductCard(product = product)
        }
    }
}