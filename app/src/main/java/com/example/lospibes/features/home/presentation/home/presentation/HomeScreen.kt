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
import coil.compose.AsyncImage
import com.example.lospibes.core.components.*
import com.example.lospibes.features.home.domain.model.TabItem
import com.example.lospibes.utils.Constants.categories
import com.example.lospibes.utils.Constants.combos
import com.example.lospibes.utils.Constants.products

@Composable
fun HomeScreen(
    onNavigateToExplore: (query: String) -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
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
    onNavigateToExplore: (query: String) -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    CategorySection(
        onNavigateToExplore = onNavigateToExplore
    )

    Spacer(modifier = Modifier.height(26.dp))

    PopularSection(
        onNavigateToDetails = onNavigateToDetails
    )

    Spacer(modifier = Modifier.height(26.dp))

    CombosSection(
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
private fun CategorySection(
    onNavigateToExplore: (query: String) -> Unit
) {
    val tabList = categories.map { category ->
        TabItem(
            name = category.name,
            icon = category.code
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

@Composable
private fun PopularSection(
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
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

    ProductListRow(
        products = products,
        favoriteProducts = products.subList(0, 1),
        onProductSelected = { selectedProduct ->
            onNavigateToDetails(false, selectedProduct.id)
        }
    )
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