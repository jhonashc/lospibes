package com.example.lospibes.features.home.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.components.*
import com.example.lospibes.utils.Constants.categories
import com.example.lospibes.utils.Constants.combos
import com.example.lospibes.utils.Constants.products

@Composable
fun HomeScreen(
    onNavigateToComboDetails: (comboId: String) -> Unit,
    onNavigateToProductDetails: (productId: String) -> Unit,
    onNavigateToSearch: (value: String) -> Unit,
) {
    StandardScaffold(
        topAppBar = {
            StandardTopAppBar(title = "Home")
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
                    onNavigateToComboDetails = onNavigateToComboDetails,
                    onNavigateToProductDetails = onNavigateToProductDetails,
                    onNavigateToSearch = onNavigateToSearch
                )
            }
        }
    }
}

@Composable
private fun Content(
    onNavigateToComboDetails: (comboId: String) -> Unit,
    onNavigateToProductDetails: (productId: String) -> Unit,
    onNavigateToSearch: (value: String) -> Unit
) {
    Header(onNavigateToSearch = onNavigateToSearch)

    Spacer(modifier = Modifier.height(26.dp))

    CategorySection(
        onNavigateToSearch = onNavigateToSearch
    )

    Spacer(modifier = Modifier.height(16.dp))

    PopularSection(
        onNavigateToProductDetails = onNavigateToProductDetails
    )

    Spacer(modifier = Modifier.height(26.dp))

    CombosSection(
        onNavigateToComboDetails = onNavigateToComboDetails
    )
}

@Composable
private fun Header(
    onNavigateToSearch: (value: String) -> Unit
) {
    SearchTextField(
        onSubmit = { value ->
            onNavigateToSearch(value)
        }
    )
}

@Composable
private fun CategorySection(
    onNavigateToSearch: (category: String) -> Unit
) {
    var selectedCategory by remember { mutableStateOf(categories[0]) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Categorías \uD83C\uDFF7",
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = "Ver todas",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Spacer(modifier = Modifier.height(6.dp))

    CategoryTabList(
        categories = categories,
        selectedCategory = selectedCategory,
        onCategorySelected = { currentCategory ->
            selectedCategory = currentCategory
        },
        onNavigateToSearch = onNavigateToSearch
    )
}

@Composable
private fun PopularSection(
    onNavigateToProductDetails: (productId: String) -> Unit,
) {
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
            text = "Ver todas",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Spacer(modifier = Modifier.height(22.dp))

    ProductList(
        products = products,
        favoriteProducts = products.subList(0, 2),
        onNavigateToProductDetails = onNavigateToProductDetails
    )
}

@Composable
private fun CombosSection(
    onNavigateToComboDetails: (comboId: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Combos \uD83D\uDCE6",
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = "Ver todas",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Spacer(modifier = Modifier.height(22.dp))

    ComboList(
        combos = combos,
        favoriteCombos = combos.subList(0, 2),
        onNavigateToComboDetails = onNavigateToComboDetails
    )
}