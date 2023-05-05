package com.example.lospibes.features.home.presentation.explore.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.R
import com.example.lospibes.core.component.*
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.toCardItem
import com.example.lospibes.features.home.domain.model.toCartItem
import com.example.lospibes.features.home.view_model.cart.CartEvent
import com.example.lospibes.features.home.view_model.cart.CartViewModel

@Composable
fun ExploreScreen(
    cartViewModel: CartViewModel,
    exploreViewModel: ExploreViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToFilter: () -> Unit,
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
        isLoading = exploreState.value.isProductLoading,
        message = exploreState.value.message
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header(
                onNavigateToHome = onNavigateToHome
            )

            Body(
                cartViewModel = cartViewModel,
                exploreState = exploreState,
                onNavigateToFilter = onNavigateToFilter,
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
            title = "Explorar",
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
    exploreState: State<ExploreState>,
    onNavigateToFilter: () -> Unit,
    onNavigateToDetails: (productId: String) -> Unit
) {
    FilterSection(
        onNavigateToFilter = onNavigateToFilter
    )

    Spacer(modifier = Modifier.height(5.dp))

    ResultSection(
        cartViewModel = cartViewModel,
        exploreState = exploreState,
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
private fun FilterSection(
    onNavigateToFilter: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier.weight(1f),
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 1.dp
            ),
            onClick = onNavigateToFilter
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.baseline_filter_alt_24
                ),
                contentDescription = "Filter Icon"
            )

            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = "Filtros",
                style = MaterialTheme.typography.titleSmall
            )
        }

        Button(
            modifier = Modifier.weight(1f),
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 1.dp
            ),
            onClick = {}
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.baseline_sort_24
                ),
                contentDescription = "Sort Icon"
            )

            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = "Ordenar",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
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