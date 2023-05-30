package com.example.lospibes.features.home.presentation.explore.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.*
import com.example.lospibes.features.home.data.dto.query.SearchProductsQueryDto
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.SearchItem
import com.example.lospibes.features.home.domain.model.toCardItem
import com.example.lospibes.features.home.domain.model.toCartItem
import com.example.lospibes.features.home.domain.model.toSearchItem
import com.example.lospibes.features.home.presentation.explore.component.ExploreBottomSheet
import com.example.lospibes.features.home.presentation.explore.component.ExploreTopBar
import com.example.lospibes.features.home.view_model.cart.CartEvent
import com.example.lospibes.features.home.view_model.cart.CartViewModel

@Composable
fun ExploreScreen(
    cartViewModel: CartViewModel,
    exploreViewModel: ExploreViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val exploreState = exploreViewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        exploreViewModel.getProducts()
    }

    LaunchedEffect(key1 = exploreState.value.query) {
        if (exploreState.value.query.isNotEmpty()) {
            exploreViewModel.onEvent(
                ExploreEvent.EnteredQuery(
                    SearchProductsQueryDto(
                        name = exploreState.value.query,
                        category = exploreState.value.searchProductsQueryDto?.category,
                        min = exploreState.value.searchProductsQueryDto?.min,
                        max = exploreState.value.searchProductsQueryDto?.max
                    )
                )
            )
        }
    }

    LaunchedEffect(key1 = exploreState.value.searchProductsQueryDto) {
        if (exploreState.value.query.isNotEmpty()) {
            exploreViewModel.searchProducts(
                searchProductsQueryDto = exploreState.value.searchProductsQueryDto
            )
        }
    }


    if (exploreState.value.isOpenBottomSheet) {
        ExploreBottomSheet(
            exploreViewModel = exploreViewModel,
            onDismissRequest = {
                exploreViewModel.onEvent(ExploreEvent.OnHideBottomSheet)
            }
        )
    }

    StandardScaffold(
        topAppBar = {
            Header(
                exploreViewModel = exploreViewModel,
                onNavigateToHome = onNavigateToHome,
                onNavigateToDetails = onNavigateToDetails
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Body(
                cartViewModel = cartViewModel,
                exploreViewModel = exploreViewModel,
                onNavigateToDetails = onNavigateToDetails
            )
        }
    }
}

@Composable
private fun Header(
    exploreViewModel: ExploreViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val exploreState = exploreViewModel.state.collectAsState()

    val searchResultList: List<SearchItem> = if (
        exploreState.value.query.isEmpty()
    ) {
        emptyList()
    } else {
        exploreState.value.searchProductList.map { it.toSearchItem() }
    }

    ExploreTopBar(
        query = exploreState.value.query,
        isActive = exploreState.value.isActive,
        searchResultList = searchResultList,
        searchWidgetState = exploreState.value.searchWidgetState,
        onNavigateToHome = onNavigateToHome,
        onSearchClick = {
            exploreViewModel.onEvent(ExploreEvent.OnSearchBarOpen)
        },
        onFilterClick = {
            exploreViewModel.onEvent(ExploreEvent.OnOpenBottomSheet)
        },
        onClose = {
            exploreViewModel.onEvent(ExploreEvent.OnSearchBarClose)
        },
        onItemClick = { selectedItemId ->
            onNavigateToDetails(selectedItemId)
            exploreViewModel.onEvent(ExploreEvent.OnSearchBarClose)
        },
        onSearch = {
            exploreViewModel.onEvent(ExploreEvent.EnteredActive(false))
        },
        onQueryChange = {
            exploreViewModel.onEvent(ExploreEvent.EnteredQueryName(it))
        },
        onActiveChange = {
            exploreViewModel.onEvent(ExploreEvent.EnteredActive(it))
        }
    )
}

@Composable
private fun Body(
    cartViewModel: CartViewModel,
    exploreViewModel: ExploreViewModel,
    onNavigateToDetails: (productId: String) -> Unit
) {
    ResultSection(
        cartViewModel = cartViewModel,
        exploreViewModel = exploreViewModel,
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
private fun ResultSection(
    cartViewModel: CartViewModel,
    exploreViewModel: ExploreViewModel,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val cartState = cartViewModel.state.collectAsState()
    val exploreState = exploreViewModel.state.collectAsState()

    val cartItemList: List<CartItem> = cartState.value.cartItemList
    val productList: List<Product> = exploreState.value.productList
    val searchProductList: List<Product> = exploreState.value.searchProductList

    val productCardList: List<CardItem> = if (
        exploreState.value.query.isNotEmpty()
    ) {
        searchProductList.map { it.toCardItem() }
    } else {
        productList.map { it.toCardItem() }
    }

    if (productCardList.isEmpty()) {
        Text(text = "Empty")
    } else {
        StandardCardListGrid(
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
}