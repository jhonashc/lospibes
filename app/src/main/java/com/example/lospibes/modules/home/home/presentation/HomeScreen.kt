package com.example.lospibes.modules.home.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.components.CategoryTabList
import com.example.lospibes.common.components.HorizontalProductList
import com.example.lospibes.common.components.VerticalProductList
import com.example.lospibes.utils.Constants.categories
import com.example.lospibes.utils.Constants.products

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Content()
        }
    }
}

@Composable
fun Content() {
    CategorySection()
    Spacer(modifier = Modifier.height(16.dp))
    PopularSection()
    Spacer(modifier = Modifier.height(26.dp))
    PromotionSection()
}

@Composable
fun CategorySection() {
    var selectedCategory by remember { mutableStateOf(categories[0]) }

    Text(
        text = "Categorias",
        style = MaterialTheme.typography.titleMedium,
    )

    Spacer(modifier = Modifier.height(6.dp))

    CategoryTabList(
        categories = categories,
        selectedCategory = selectedCategory,
        onCategorySelected = { currentCategory ->
            selectedCategory = currentCategory
        }
    )
}

@Composable
fun PopularSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Popular \uD83D\uDD25",
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = "See all",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Spacer(modifier = Modifier.height(22.dp))

    HorizontalProductList(products = products)
}

@Composable
fun PromotionSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Promociones ⏳",
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = "See all",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Spacer(modifier = Modifier.height(22.dp))

    VerticalProductList(products = products)
}