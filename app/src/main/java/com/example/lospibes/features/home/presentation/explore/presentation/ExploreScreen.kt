package com.example.lospibes.features.home.presentation.explore.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lospibes.R
import com.example.lospibes.core.component.SearchTopBar
import com.example.lospibes.core.component.StandardTabList
import com.example.lospibes.core.component.StandardTopBar
import com.example.lospibes.features.home.component.ProductListGrid
import com.example.lospibes.features.home.domain.model.TabItem
import com.example.lospibes.utils.Constants.categories
import com.example.lospibes.utils.Constants.products

@Composable
fun ExploreScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToFilter: () -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(
            onNavigateToHome = onNavigateToHome,
            onNavigateToFilter = onNavigateToFilter
        )

        Body(
            onNavigateToDetails = onNavigateToDetails
        )
    }
}

@Composable
private fun Header(
    onNavigateToHome: () -> Unit,
    onNavigateToFilter: () -> Unit
) {
    var value by remember { mutableStateOf("") }
    val onValueChange = { newValue: String -> value = newValue }

    var isVisibleSearchBar by remember { mutableStateOf(false) }

    if (!isVisibleSearchBar) {
        StandardTopBar(
            title = "Explorar",
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
            filterIcon = {
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.outline
                    ),
                    onClick = {
                        onNavigateToFilter()
                        isVisibleSearchBar = true
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_filter_list_24),
                        contentDescription = "Filter Icon"
                    )
                }
            },
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
    CategorySection()

    ResultSection(
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
private fun CategorySection() {
    val tabList = categories.map { category ->
        TabItem(
            name = category.name,
            icon = category.code
        )
    }

    var selectedTab by remember { mutableStateOf(tabList[0]) }
    val onTabSelected = { newTab: TabItem -> selectedTab = newTab }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        StandardTabList(
            tabList = tabList,
            selectedTab = selectedTab,
            onTabSelected = onTabSelected
        )
    }
}

@Composable
fun ResultSection(
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    ProductListGrid(
        products = products,
        favoriteProducts = products.subList(0, 2),
        onProductSelected = { selectedProduct ->
            onNavigateToDetails(false, selectedProduct.id)
        }
    )
}