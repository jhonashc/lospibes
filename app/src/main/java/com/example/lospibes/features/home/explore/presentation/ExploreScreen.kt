package com.example.lospibes.features.home.explore.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.components.*
import com.example.lospibes.common.domain.model.ChipItem
import com.example.lospibes.utils.Constants.products

@Composable
fun ExploreScreen(
    onNavigateToExploreFilter: () -> Unit,
) {
    StandardScaffold(
        topAppBar = {
            StandardTopAppBar(
                title = "Explorar"
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
                Content(
                    onNavigateToExploreFilter = onNavigateToExploreFilter
                )
            }
        }
    }
}

@Composable
private fun Content(
    onNavigateToExploreFilter: () -> Unit
) {
    Header(
        onNavigateToExploreFilter = onNavigateToExploreFilter
    )

    Spacer(modifier = Modifier.height(26.dp))

    RecentSection()
}

@Composable
private fun Header(
    onNavigateToExploreFilter: () -> Unit
) {
    var value by remember { mutableStateOf("") }
    val onValueChange = { newValue: String -> value = newValue }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        SearchTextField(
            value = value,
            onValueChange = onValueChange,
            showFilter = true,
            onClick = onNavigateToExploreFilter,
            onSubmit = {}
        )
    }
}

@Composable
private fun RecentSection() {
    val chipList: List<ChipItem> = listOf(
        ChipItem(
            name = "Hamburger",
            icon = Icons.Filled.Close
        ),
        ChipItem(
            name = "Pizza",
            icon = Icons.Filled.Close
        ),
        ChipItem(
            name = "Double Cheese Burger",
            icon = Icons.Filled.Close
        ),
        ChipItem(
            name = "Chicken wings",
            icon = Icons.Filled.Close
        ),
        ChipItem(
            name = "Chili Hot Dog",
            icon = Icons.Filled.Close
        )
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Recientes ⏱️",
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(10.dp))

        StandardChipList(
            chipList = chipList,
            onClick = {}
        )
    }
}

@Composable
private fun Results() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Resultados \uD83D\uDD25",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        DetailedProductList(
            products = products,
            favoriteProducts = products.subList(0, 2),
            onNavigateToProductDetails = {}
        )
    }
}