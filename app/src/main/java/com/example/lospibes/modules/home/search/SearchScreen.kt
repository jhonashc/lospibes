package com.example.lospibes.modules.home.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.components.ProductList
import com.example.lospibes.common.components.SearchTextField
import com.example.lospibes.common.components.StandardScaffold
import com.example.lospibes.common.components.StandardTopAppBar
import com.example.lospibes.utils.Constants

@Composable
fun SearchScreen(
    onNavigateToHome: () -> Unit
) {
    StandardScaffold(
        topAppBar = {
            StandardTopAppBar(
                title = "Búsqueda",
                onNavigateToHome = onNavigateToHome
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                Content()
            }
        }
    }
}

@Composable
fun Content() {
    Header()
    Spacer(modifier = Modifier.height(26.dp))
    RecentSection()
}

@Composable
fun Header() {
    SearchTextField(
        onSubmit = {}
    )
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
            text = "Recientes ⏱️",
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = "See all",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Spacer(modifier = Modifier.height(22.dp))

    ProductList(
        products = Constants.products,
        onNavigateToProductDetails = {}
    )
}