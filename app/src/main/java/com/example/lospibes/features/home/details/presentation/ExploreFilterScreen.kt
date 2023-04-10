package com.example.lospibes.features.home.details.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.components.StandardFlowRow
import com.example.lospibes.common.components.StandardScaffold
import com.example.lospibes.common.components.StandardTopAppBar
import com.example.lospibes.common.domain.model.Category
import com.example.lospibes.utils.Constants.categories

@Composable
fun ExploreFilterScreen(
    onNavigateToExplore: () -> Unit
) {
    StandardScaffold(
        topAppBar = {
            StandardTopAppBar(
                title = "Filtros",
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateToExplore
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
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

            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(start = 20.dp, bottom = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    modifier = Modifier.padding(6.dp),
                    text = "Aplicar filtros",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
private fun Content() {
    PriceRangeSection()
    Spacer(modifier = Modifier.height(26.dp))
    CategorySection()
}

@Composable
private fun PriceRangeSection() {
    Text(
        modifier = Modifier.padding(horizontal = 20.dp),
        text = "Rango de precio",
        style = MaterialTheme.typography.titleMedium,
    )

    Spacer(modifier = Modifier.height(10.dp))

    val itemList = listOf("$5", "$10", "$20", "$40")
    var selectedItem by remember { mutableStateOf(itemList[0]) }
    val onItemSelected = { price: String -> selectedItem = price }

    StandardFlowRow(
        itemList = itemList,
        selectedItem = selectedItem,
        onItemSelected = onItemSelected
    )
}

@Composable
private fun CategorySection() {
    Text(
        modifier = Modifier.padding(horizontal = 20.dp),
        text = "Categorías",
        style = MaterialTheme.typography.titleMedium,
    )

    Spacer(modifier = Modifier.height(10.dp))

    val itemList = categories.map { category: Category -> category.name }
    var selectedItem by remember { mutableStateOf(itemList[0]) }
    val onItemSelected = { categoryName: String -> selectedItem = categoryName }

    StandardFlowRow(
        itemList = itemList,
        selectedItem = selectedItem,
        onItemSelected = onItemSelected
    )
}