package com.example.lospibes.features.home.presentation.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.StandardCardListColumn
import com.example.lospibes.core.component.StandardCardListRow
import com.example.lospibes.core.component.StandardScaffold
import com.example.lospibes.core.component.StandardScrollableColumnContainer
import com.example.lospibes.core.component.StandardTabList
import com.example.lospibes.features.home.domain.model.*
import com.example.lospibes.features.home.presentation.home.component.HomeBottomSheet
import com.example.lospibes.features.home.presentation.home.component.HomeTopBar
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

    if (homeState.value.isOpenBottomSheet) {
        HomeBottomSheet(
            addressList = homeState.value.addressList.map { it.name },
            onDismissRequest = {
                homeViewModel.onEvent(HomeEvent.OnHideBottomSheet)
            }
        )
    }

    StandardScaffold(
        topAppBar = {
            HomeTopBar(
                currentAddress = "Address here!",
                onOpenBottomSheet = {
                    homeViewModel.onEvent(HomeEvent.OnOpenBottomSheet)
                }
            )
        }
    ) {
        StandardScrollableColumnContainer(
            status = homeState.value.status,
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
                    homeViewModel = homeViewModel,
                    onNavigateToExplore = onNavigateToExplore,
                    onNavigateToDetails = onNavigateToDetails
                )
            }
        }
    }
}

@Composable
private fun Body(
    cartViewModel: CartViewModel,
    homeViewModel: HomeViewModel,
    onNavigateToExplore: (query: String) -> Unit,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val homeState = homeViewModel.state.collectAsState()

    val categoryList: List<Category> = homeState.value.categoryList
    val popularList: List<Product> = homeState.value.productList

    if (categoryList.isNotEmpty()) {
        CategorySection(
            homeViewModel = homeViewModel,
            onNavigateToExplore = onNavigateToExplore
        )
    }

    if (popularList.isNotEmpty()) {
        Spacer(modifier = Modifier.height(14.dp))

        ProductSection(
            cartViewModel = cartViewModel,
            homeState = homeState,
            onNavigateToDetails = onNavigateToDetails
        )
    }

    if (popularList.isNotEmpty()) {
        Spacer(modifier = Modifier.height(14.dp))

        PopularSection(
            cartViewModel = cartViewModel,
            homeState = homeState,
            onNavigateToDetails = onNavigateToDetails
        )
    }
}

@Composable
private fun CategorySection(
    homeViewModel: HomeViewModel,
    onNavigateToExplore: (query: String) -> Unit
) {
    val homeState = homeViewModel.state.collectAsState()

    val defaultTab = TabItem("Todas")
    val categoryList: List<Category> = homeState.value.categoryList

    val tabList = mutableListOf<TabItem>().apply {
        add(defaultTab)
        addAll(categoryList.map { it.toTabItem() })
    }.toList()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Categorías",
            style = MaterialTheme.typography.titleMedium,
        )

        TextButton(
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Ver todas",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        StandardTabList(
            tabList = tabList,
            selectedTab = homeState.value.selectedTab,
            onTabSelected = { newSelectedTab ->
                homeViewModel.onEvent(HomeEvent.EnteredCategory(newSelectedTab))
                /* onNavigateToExplore(newSelectedTab.name) */
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Productos",
            style = MaterialTheme.typography.titleMedium,
        )

        TextButton(
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Ver todos",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    Spacer(modifier = Modifier.height(12.dp))

    StandardCardListRow(
        cardItemList = productCardList,
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

@Composable
fun PopularSection(
    cartViewModel: CartViewModel,
    homeState: State<HomeState>,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val cartState = cartViewModel.state.collectAsState()

    val cartItemList: List<CartItem> = cartState.value.cartItemList
    val popularProductList: List<Product> = homeState.value.popularProductList

    val popularProductCardList: List<CardItem> = popularProductList.map { it.toCardItem() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Populares",
                style = MaterialTheme.typography.titleMedium,
            )

            TextButton(
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Ver todos",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        StandardCardListColumn(
            cardItemList = popularProductCardList,
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
}