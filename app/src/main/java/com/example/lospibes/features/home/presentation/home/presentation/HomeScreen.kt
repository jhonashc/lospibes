package com.example.lospibes.features.home.presentation.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.StandardBoxContainer
import com.example.lospibes.core.component.StandardCardListRow
import com.example.lospibes.core.component.StandardTabList
import com.example.lospibes.features.home.domain.model.*
import com.example.lospibes.features.home.viewmodel.cart.CartEvent
import com.example.lospibes.features.home.viewmodel.cart.CartViewModel

@Composable
fun HomeScreen(
    cartViewModel: CartViewModel,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onNavigateToExplore: (query: String) -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val homeState = homeViewModel.state.collectAsState()

    /* Temporal */
    val userId = "a4d0dea5-2b10-42b9-a930-a8faec563e10"

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getCategories()
        homeViewModel.getCombos()
        homeViewModel.getProducts()

        /* Temporal */
        homeViewModel.getFavoriteCombos(userId)
        homeViewModel.getFavoriteProducts(userId)
    }

    StandardBoxContainer(
        isLoading = homeState.value.isCategoryLoading &&
                homeState.value.isComboLoading &&
                homeState.value.isProductLoading &&
                homeState.value.isFavoriteComboLoading &&
                homeState.value.isFavoriteProductLoading,
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
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val categoryList: List<Category> = homeState.value.categoryList
    val popularList: List<Product> = homeState.value.productList
    val comboList: List<Combo> = homeState.value.comboList

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

    if (comboList.isNotEmpty()) {
        Spacer(modifier = Modifier.height(26.dp))

        CombosSection(
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
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val cartState = cartViewModel.state.collectAsState()

    val cartItemList: List<CartItem> = cartState.value.cartItemList
    val productList: List<Product> = homeState.value.productList
    val favoriteProductList: List<Product> = homeState.value.favoriteProductList

    val productCardList: List<CardItem> = productList.map { it.toCardItem() }
    val favoriteProductCardList: List<CardItem> = favoriteProductList.map { it.toCardItem() }

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
        favoriteCardItemList = favoriteProductCardList,
        cartItemList = cartItemList,
        onCardItemSelected = { selectedCardItem ->
            onNavigateToDetails(selectedCardItem.isCombo, selectedCardItem.id)
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
private fun CombosSection(
    cartViewModel: CartViewModel,
    homeState: State<HomeState>,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val cartState = cartViewModel.state.collectAsState()

    val cartItemList: List<CartItem> = cartState.value.cartItemList
    val comboList: List<Combo> = homeState.value.comboList
    val favoriteComboList: List<Combo> = homeState.value.favoriteComboList

    val comboCardList: List<CardItem> = comboList.map { it.toCardItem() }
    val favoriteComboCardList: List<CardItem> = favoriteComboList.map { it.toCardItem() }

    val cardItemList: MutableList<CardItem> = mutableListOf()
    cardItemList.addAll(comboCardList)

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
            text = "Ver todos",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Spacer(modifier = Modifier.height(22.dp))

    StandardCardListRow(
        cardItemList = cardItemList,
        favoriteCardItemList = favoriteComboCardList,
        cartItemList = cartItemList,
        onCardItemSelected = { selectedCardItem ->
            onNavigateToDetails(selectedCardItem.isCombo, selectedCardItem.id)
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