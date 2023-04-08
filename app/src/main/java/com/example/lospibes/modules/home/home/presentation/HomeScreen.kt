package com.example.lospibes.modules.home.home.presentation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.components.CategoryTabList
import com.example.lospibes.common.components.HorizontalProductList
import com.example.lospibes.common.components.SearchTextField
import com.example.lospibes.utils.Constants.categories
import com.example.lospibes.utils.Constants.hamburgers
import com.example.lospibes.utils.Constants.products

@Composable
fun HomeScreen(
    onNavigateToDetail: (productId: String) -> Unit,
    onNavigateToSearch: (value: String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .clickable { onNavigateToDetail("1") }
        ) {
            Content(onNavigateToSearch = onNavigateToSearch)
        }
    }
}

@Composable
fun Content(
    onNavigateToSearch: (value: String) -> Unit
) {
    Header(onNavigateToSearch = onNavigateToSearch)
    Spacer(modifier = Modifier.height(26.dp))
    CategorySection()
    Spacer(modifier = Modifier.height(16.dp))
    PopularSection()
    Spacer(modifier = Modifier.height(26.dp))
    RecentSection()
    Spacer(modifier = Modifier.height(26.dp))
    PromotionSection()
}

@Composable
fun Header(
    onNavigateToSearch: (value: String) -> Unit
) {
    SearchTextField(
        onSubmit = { value ->
            onNavigateToSearch(value)
        }
    )
}

@Composable
fun CategorySection() {
    var selectedCategory by remember { mutableStateOf(categories[0]) }

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        text = "Categorías \uD83C\uDFF7️",
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Populares \uD83D\uDD25",
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
fun RecentSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Hamgurguesas \uD83C\uDF54",
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = "See all",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Spacer(modifier = Modifier.height(22.dp))

    HorizontalProductList(products = hamburgers)
}

@Composable
fun PromotionSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Promociones ⏱️",
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