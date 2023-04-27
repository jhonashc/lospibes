package com.example.lospibes.features.home.presentation.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.lospibes.core.component.StandardBoxContainer
import com.example.lospibes.core.component.StandardTabList
import com.example.lospibes.features.home.component.ComboListRow
import com.example.lospibes.features.home.component.ProductListRow
import com.example.lospibes.features.home.domain.model.*
import com.example.lospibes.features.home.viewmodel.cart.CartViewModel

@Composable
fun HomeScreen(
    cartViewModel: CartViewModel,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onNavigateToExplore: (query: String) -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val homeState = homeViewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        homeViewModel.getCategories()
        homeViewModel.getCombos()
        homeViewModel.getProducts()
    }

    StandardBoxContainer(
        isLoading = homeState.value.isCategoryLoading &&
                homeState.value.isComboLoading &&
                homeState.value.isProductLoading,
        message = homeState.value.message
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp)
        ) {
            Header(
                homeState = homeState
            )

            Spacer(modifier = Modifier.height(26.dp))

            Body(
                homeState = homeState,
                onNavigateToExplore = onNavigateToExplore,
                onNavigateToDetails = onNavigateToDetails
            )
        }
    }
}

@Composable
private fun Header(
    homeState: State<HomeState>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Tu ubicación",
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )

                IconButton(
                    modifier = Modifier.size(25.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.outline,
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "KeyboardArrowDown",
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Location",
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "2009, Robert Browning St, Mosaic at Monastery",
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1
                )
            }
        }

        AsyncImage(
            modifier = Modifier
                .size(45.dp)
                .clip(MaterialTheme.shapes.extraLarge),
            model = "https://images.pexels.com/photos/3778361/pexels-photo-3778361.jpeg",
            contentDescription = "Profile",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun Body(
    homeState: State<HomeState>,
    onNavigateToExplore: (query: String) -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    CategorySection(
        homeState = homeState,
        onNavigateToExplore = onNavigateToExplore
    )

    Spacer(modifier = Modifier.height(26.dp))

    PopularSection(
        homeState = homeState,
        onNavigateToDetails = onNavigateToDetails
    )

    Spacer(modifier = Modifier.height(26.dp))

    CombosSection(
        homeState = homeState,
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
private fun CategorySection(
    homeState: State<HomeState>,
    onNavigateToExplore: (query: String) -> Unit
) {
    val categoryList: List<Category> = homeState.value.categoryList

    if (categoryList.isNotEmpty()) {
        val tabList = categoryList.map { category ->
            TabItem(
                name = category.name
            )
        }

        var selectedTab by remember { mutableStateOf(tabList[0]) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Categorías",
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = "Ver todas",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp)
        ) {
            StandardTabList(
                tabList = tabList,
                selectedTab = selectedTab,
                onTabSelected = { newSelectedTab ->
                    selectedTab = newSelectedTab
                    onNavigateToExplore(newSelectedTab.name)
                },
            )
        }
    }
}

@Composable
private fun PopularSection(
    homeState: State<HomeState>,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val productList: List<Product> = homeState.value.productList

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Populares",
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = "Ver todas",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Spacer(modifier = Modifier.height(22.dp))

    ProductListRow(
        products = productList,
        onProductSelected = { selectedProduct ->
            onNavigateToDetails(false, selectedProduct.id)
        }
    )
}

@Composable
private fun CombosSection(
    homeState: State<HomeState>,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val comboList: List<Combo> = homeState.value.comboList

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Combos",
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = "Ver todas",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Spacer(modifier = Modifier.height(22.dp))

    ComboListRow(
        combos = comboList,
        onComboSelected = { selectedCombo ->
            onNavigateToDetails(true, selectedCombo.id)
        }
    )
}