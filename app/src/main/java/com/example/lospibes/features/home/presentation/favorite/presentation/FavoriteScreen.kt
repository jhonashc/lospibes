package com.example.lospibes.features.home.presentation.favorite.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.SearchTopBar
import com.example.lospibes.core.component.StandardCardListGrid
import com.example.lospibes.core.component.StandardColumnContainer
import com.example.lospibes.core.component.StandardTopBar
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.toCardItem
import com.example.lospibes.features.home.domain.model.toCartItem
import com.example.lospibes.features.home.viewmodel.cart.CartEvent
import com.example.lospibes.features.home.viewmodel.cart.CartViewModel

@Composable
fun FavoriteScreen(
    cartViewModel: CartViewModel,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val favoriteState = favoriteViewModel.state.collectAsState()

    /* Temporal */
    val userId = "a4d0dea5-2b10-42b9-a930-a8faec563e10"

    LaunchedEffect(key1 = Unit) {
        favoriteViewModel.getFavoriteProducts(userId)
    }

    StandardColumnContainer(
        isLoading = favoriteState.value.isFavoriteProductLoading,
        message = favoriteState.value.message
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header(
                onNavigateToHome = onNavigateToHome
            )

            Body(
                cartViewModel = cartViewModel,
                favoriteViewModel = favoriteViewModel,
                onNavigateToDetails = onNavigateToDetails
            )
        }
    }
}

@Composable
private fun Header(
    onNavigateToHome: () -> Unit
) {
    var value by remember { mutableStateOf("") }
    val onValueChange = { newValue: String -> value = newValue }

    var isVisibleSearchBar by remember { mutableStateOf(false) }

    if (!isVisibleSearchBar) {
        StandardTopBar(
            title = "Favoritos",
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
    cartViewModel: CartViewModel,
    favoriteViewModel: FavoriteViewModel,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val favoriteState = favoriteViewModel.state.collectAsState()

    val favoriteProductList: List<Product> = favoriteState.value.favoriteProductList

    if (favoriteProductList.isNotEmpty()) {
        FavoriteSection(
            cartViewModel = cartViewModel,
            favoriteViewModel = favoriteViewModel,
            onNavigateToDetails = onNavigateToDetails
        )
    }
}

@Composable
fun FavoriteSection(
    cartViewModel: CartViewModel,
    favoriteViewModel: FavoriteViewModel,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val cartState = cartViewModel.state.collectAsState()
    val favoriteState = favoriteViewModel.state.collectAsState()

    val cartItemList: List<CartItem> = cartState.value.cartItemList
    val favoriteProductList: List<Product> = favoriteState.value.favoriteProductList

    val productCardList: List<CardItem> = favoriteProductList.map { it.toCardItem() }

    StandardCardListGrid(
        cardItemList = productCardList,
        favoriteCardItemList = productCardList,
        cartItemList = cartItemList,
        showFavIcon = true,
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