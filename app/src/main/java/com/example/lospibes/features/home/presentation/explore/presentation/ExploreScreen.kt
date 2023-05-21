package com.example.lospibes.features.home.presentation.explore.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.*
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.toCardItem
import com.example.lospibes.features.home.domain.model.toCartItem
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
    val queryState = exploreViewModel.queryState.collectAsState()

    LaunchedEffect(key1 = queryState.value) {
        exploreViewModel.getProducts(
            getProductsQueryDto = queryState.value.getProductsQueryDto
        )
    }

    StandardColumnContainer(
        isLoading = exploreState.value.isLoading,
        message = exploreState.value.message
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header(
                exploreViewModel = exploreViewModel,
                onNavigateToHome = onNavigateToHome
            )

            Body(
                cartViewModel = cartViewModel,
                exploreState = exploreState,
                onNavigateToDetails = onNavigateToDetails
            )
        }
    }
}

@Composable
private fun Header(
    exploreViewModel: ExploreViewModel,
    onNavigateToHome: () -> Unit
) {
    val exploreState = exploreViewModel.state.collectAsState()

    /* Temporal */
    val context = LocalContext.current

    ExploreTopBar(
        searchText = exploreState.value.searchText,
        searchResultList = emptyList(),
        searchWidgetState = exploreState.value.searchWidgetState,
        onNavigateToHome = onNavigateToHome,
        onSearchClick = {
            exploreViewModel.onEvent(ExploreEvent.OnSearchBarClick)
        },
        onClose = {
            exploreViewModel.onEvent(ExploreEvent.OnSearchBarClose)
        },
        onSubmit = {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        },
        onValueChange = {
            exploreViewModel.onEvent(ExploreEvent.EnteredSearchBarText(it))
        },
    )
}

@Composable
private fun Body(
    cartViewModel: CartViewModel,
    exploreState: State<ExploreState>,
    onNavigateToDetails: (productId: String) -> Unit
) {
    ResultSection(
        cartViewModel = cartViewModel,
        exploreState = exploreState,
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
private fun ResultSection(
    cartViewModel: CartViewModel,
    exploreState: State<ExploreState>,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val cartState = cartViewModel.state.collectAsState()

    val cartItemList: List<CartItem> = cartState.value.cartItemList
    val productList: List<Product> = exploreState.value.productList

    val productCardList: List<CardItem> = productList.map { it.toCardItem() }

    StandardCardListGrid(
        cardItemList = productCardList,
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