package com.example.lospibes.features.home.presentation.filter.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.lospibes.core.components.StandardFlowRow
import com.example.lospibes.core.components.StandardRangeSlider
import com.example.lospibes.core.components.StandardTopBar
import com.example.lospibes.features.home.presentation.filter.component.RecentList
import com.example.lospibes.utils.Constants.categories

@Composable
fun FilterScreen(
    onNavigateToExplore: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Header(
                onNavigateToExplore = onNavigateToExplore
            )

            Body()
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
private fun Body() {
    PriceSection()

    Spacer(modifier = Modifier.height(26.dp))

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    )

    Spacer(modifier = Modifier.height(26.dp))

    CategorySection()

    Spacer(modifier = Modifier.height(26.dp))

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    )

    Spacer(modifier = Modifier.height(18.dp))

    RecentSection()
}

@Composable
private fun PriceSection() {
    val valueRange = 5.0f..30.0f
    var value by remember { mutableStateOf(5.0f..10.0f) }

    val onValueChange = { newValue: ClosedFloatingPointRange<Float> ->
        value = newValue
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Rango de precio",
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(6.dp))

        StandardRangeSlider(
            value = value,
            valueRange = valueRange,
            onValueChange = onValueChange,
            steps = 4
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = value.toString(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.outline,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun CategorySection() {
    var currentCategory by remember { mutableStateOf("") }
    val categoryNames: List<String> = categories.map { category -> category.name }

    val onItemSelected = { newCategory: String -> currentCategory = newCategory }

    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Categorías",
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(18.dp))

        StandardFlowRow(
            itemList = categoryNames,
            selectedItem = currentCategory,
            onItemSelected = onItemSelected
        )
    }
}

@Composable
fun RecentSection() {
    val recentSearch = listOf(
        "Tomato",
        "Apple",
        "Hamburger",
        "Pizza",
        "Pastel"
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Búsquedas recientes",
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(10.dp))

        RecentList(
            itemList = recentSearch,
            onItemSelected = {}
        )
    }
}