package com.example.lospibes.features.home.presentation.filter.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.StandardBoxContainer
import com.example.lospibes.core.component.StandardFlowRow
import com.example.lospibes.core.component.StandardTopBar
import com.example.lospibes.features.home.presentation.filter.component.MaxPriceTextField
import com.example.lospibes.features.home.presentation.filter.component.MinPriceTextField

@Composable
fun FilterScreen(
    filterViewModel: FilterViewModel = hiltViewModel(),
    onNavigateToExplore: () -> Unit
) {
    val filterState = filterViewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        filterViewModel.getCategories()
    }

    StandardBoxContainer(
        isLoading = filterState.value.isCategoryLoading,
        message = filterState.value.message
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Header(
                onNavigateToExplore = onNavigateToExplore
            )

            Body(
                filterViewModel = filterViewModel
            )
        }
    }
}

@Composable
private fun Header(
    onNavigateToExplore: () -> Unit
) {
    StandardTopBar(
        title = "Filtros",
        navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = "Close Icon"
            )
        },
        actions = {
            IconButton(
                modifier = Modifier.padding(end = 5.dp),
                onClick = { onNavigateToExplore() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done Icon"
                )
            }
        },
        onBackTo = onNavigateToExplore
    )
}

@Composable
private fun Body(
    filterViewModel: FilterViewModel
) {
    PriceSection()

    Spacer(modifier = Modifier.height(26.dp))

    CategorySection(
        filterViewModel = filterViewModel
    )
}

@Composable
private fun PriceSection() {
    var minValue by remember { mutableStateOf("") }
    var maxValue by remember { mutableStateOf("") }

    val onMinValueChange = { newMinValue: String -> minValue = newMinValue }
    val onMaxValueChange = { newMinValue: String -> maxValue = newMinValue }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Rango de precio",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MinPriceTextField(
                modifier = Modifier.weight(1f),
                value = minValue,
                onValueChange = onMinValueChange
            )

            MaxPriceTextField(
                modifier = Modifier.weight(1f),
                value = maxValue,
                onValueChange = onMaxValueChange
            )
        }
    }
}

@Composable
private fun CategorySection(
    filterViewModel: FilterViewModel
) {
    val filterState = filterViewModel.state.collectAsState()

    val categoryNameList: List<String> = filterState.value.categoryList.map {
        it.name
    }

    var currentCategory by remember { mutableStateOf("") }

    val onItemSelected = { newCategory: String -> currentCategory = newCategory }

    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Categorías",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(18.dp))

        StandardFlowRow(
            itemList = categoryNameList,
            selectedItem = currentCategory,
            onItemSelected = onItemSelected
        )
    }
}