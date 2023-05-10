package com.example.lospibes.features.home.presentation.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.StandardCardListRow
import com.example.lospibes.core.component.StandardScrollableColumnContainer
import com.example.lospibes.core.component.StandardTabList
import com.example.lospibes.features.home.domain.model.*
import com.example.lospibes.features.home.view_model.cart.CartEvent
import com.example.lospibes.features.home.view_model.cart.CartViewModel

@Composable
fun HomeScreen(
    cartViewModel: CartViewModel,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onNavigateToExplore: (query: String) -> Unit,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val homeState = homeViewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getCategories()
        homeViewModel.getProducts()
    }

    StandardScrollableColumnContainer(
        isLoading = homeState.value.isLoading,
        message = homeState.value.message
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp)
        ) {
            Body(
                cartViewModel = cartViewModel,
                homeState = homeState,
                onNavigateToExplore = onNavigateToExplore,
                onNavigateToDetails = onNavigateToDetails
            )
        }
    }
}

@Composable
private fun Body(
    cartViewModel: CartViewModel,
    homeState: State<HomeState>,
    onNavigateToExplore: (query: String) -> Unit,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val categoryList: List<Category> = homeState.value.categoryList
    val popularList: List<Product> = homeState.value.productList

    if (categoryList.isNotEmpty()) {
        CategorySection(
            categoryList = categoryList,
            onNavigateToExplore = onNavigateToExplore
        )
    }

    if (popularList.isNotEmpty()) {
        Spacer(modifier = Modifier.height(26.dp))

        ProductSection(
            cartViewModel = cartViewModel,
            homeState = homeState,
            onNavigateToDetails = onNavigateToDetails
        )
    }
}

@Composable
private fun CategorySection(
    categoryList: List<Category>,
    onNavigateToExplore: (query: String) -> Unit
) {
    val tabList: List<TabItem> = categoryList.map { it.toTabItem() }

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

    Spacer(modifier = Modifier.height(7.dp))

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
            }
        )
    }
}

@Composable
private fun ProductSection(
    cartViewModel: CartViewModel,
    homeState: State<HomeState>,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val cartState = cartViewModel.state.collectAsState()

    val cartItemList: List<CartItem> = cartState.value.cartItemList
    val productList: List<Product> = homeState.value.productList

    val productCardList: List<CardItem> = productList.map { it.toCardItem() }

    val cardItemList: MutableList<CardItem> = mutableListOf()
    cardItemList.addAll(productCardList)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Productos",
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = "Ver todos",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Spacer(modifier = Modifier.height(22.dp))

    StandardCardListRow(
        cardItemList = cardItemList,
        favoriteCardItemList = emptyList(),
        cartItemList = cartItemList,
        onCardItemSelected = { selectedCardItem ->
            onNavigateToDetails(selectedCardItem.id)
        },
        onAddOrRemoveClick = { selectedCardItem ->
            val isOnTheCart = cartItemList.indexOfFirst { it.id == selectedCardItem.id }

            if (isOnTheCart != -1) {
                cartViewModel.onEvent(
                    CartEvent.RemoveFromCart(selectedCardItem.toCartItem())
                )
            } else {
                cartViewModel.onEvent(
                    CartEvent.AddToCart(selectedCardItem.toCartItem())
                )
            }
        }
    )
}