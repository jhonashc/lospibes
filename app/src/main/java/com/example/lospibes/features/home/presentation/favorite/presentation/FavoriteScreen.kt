package com.example.lospibes.features.home.presentation.favorite.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.StandardCardListGrid
import com.example.lospibes.core.component.StandardColumnContainer
import com.example.lospibes.core.component.StandardNotAuthenticated
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.toCardItem
import com.example.lospibes.features.home.domain.model.toCartItem
import com.example.lospibes.features.home.presentation.favorite.component.FavoriteTopBar
import com.example.lospibes.features.home.view_model.cart.CartEvent
import com.example.lospibes.features.home.view_model.cart.CartViewModel

@Composable
fun FavoriteScreen(
    authViewModel: AuthViewModel,
    cartViewModel: CartViewModel,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val authState = authViewModel.state.collectAsState()
    val favoriteState = favoriteViewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if (authState.value.isAuthenticated) {
            favoriteViewModel.getFavoriteProducts(
                userId = authState.value.userId
            )
        }
    }

    if (!authState.value.isAuthenticated) {
        StandardNotAuthenticated()
    } else {
        StandardColumnContainer(
            isLoading = favoriteState.value.isLoading,
            message = favoriteState.value.message
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Header(
                    favoriteViewModel = favoriteViewModel,
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
}

@Composable
private fun Header(
    favoriteViewModel: FavoriteViewModel,
    onNavigateToHome: () -> Unit
) {
    val favoriteState = favoriteViewModel.state.collectAsState()

    /* Temporal */
    val context = LocalContext.current

    FavoriteTopBar(
        searchText = favoriteState.value.searchText,
        searchResultList = emptyList(),
        searchWidgetState = favoriteState.value.searchWidgetState,
        onNavigateToHome = onNavigateToHome,
        onSearchClick = {
            favoriteViewModel.onEvent(FavoriteEvent.OnSearchBarClick)
        },
        onClose = {
            favoriteViewModel.onEvent(FavoriteEvent.OnSearchBarClose)
        },
        onSubmit = {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        },
        onValueChange = {
            favoriteViewModel.onEvent(FavoriteEvent.EnteredSearchBarText(it))
        },
    )
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