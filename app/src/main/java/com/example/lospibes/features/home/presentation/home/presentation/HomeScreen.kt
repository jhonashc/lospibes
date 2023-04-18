package com.example.lospibes.features.home.presentation.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.lospibes.core.component.StandardTabList
import com.example.lospibes.features.home.component.ComboListRow
import com.example.lospibes.features.home.component.ProductListRow
import com.example.lospibes.features.home.domain.model.Category
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.TabItem
import com.example.lospibes.utils.Constants.combos

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToExplore: (query: String) -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val state = viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Header()

            Spacer(modifier = Modifier.height(26.dp))

            Body(
                state = state,
                onNavigateToExplore = onNavigateToExplore,
                onNavigateToDetails = onNavigateToDetails
            )
        }
    }
}

@Composable
private fun Header() {
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
    state: State<HomeState>,
    onNavigateToExplore: (query: String) -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    CategorySection(
        state = state,
        onNavigateToExplore = onNavigateToExplore
    )

    Spacer(modifier = Modifier.height(26.dp))

    PopularSection(
        state = state,
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
private fun CategorySection(
    state: State<HomeState>,
    onNavigateToExplore: (query: String) -> Unit
) {
    val categoryList: List<Category> = state.value.categoryList

    if (state.value.isCategoryLoading) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        val tabList = categoryList.map { category ->
            TabItem(
                name = category.name,
                icon = category.emojiCode
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
    state: State<HomeState>,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val productList: List<Product> = state.value.productList

    if (state.value.isProductLoading) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
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

        ProductListRow(
            products = productList,
            onProductSelected = { selectedProduct ->
                onNavigateToDetails(false, selectedProduct.id)
            }
        )
    }
}

@Composable
private fun CombosSection(
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
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

    ComboListRow(
        combos = combos,
        favoriteCombos = combos.subList(0, 2),
        onComboSelected = { selectedCombo ->
            onNavigateToDetails(true, selectedCombo.id)
        }
    )
}