package com.example.lospibes.features.home.presentation.product.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.lospibes.core.component.StandardCardListRow
import com.example.lospibes.core.component.StandardFlowRow
import com.example.lospibes.core.component.StandardScaffold
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.model.Category
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.toCardItem
import com.example.lospibes.features.home.domain.model.toCartItem
import com.example.lospibes.features.home.presentation.product.component.ProductTopBar
import com.example.lospibes.features.home.view_model.cart.CartEvent
import com.example.lospibes.features.home.view_model.cart.CartViewModel
import com.example.lospibes.utils.capitalizeText
import com.example.lospibes.utils.formatNumber

@Composable
fun ProductScreen(
    authViewModel: AuthViewModel,
    cartViewModel: CartViewModel,
    productViewModel: ProductViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val authState = authViewModel.state.collectAsState()
    val productState = productViewModel.state.collectAsState()

    val product: Product? = productState.value.product

    LaunchedEffect(key1 = product) {
        if (product != null) {
            if (authState.value.isAuthenticated) {
                productViewModel.getFavoriteProduct(
                    productId = product.id,
                    userId = authState.value.userId
                )
            }

            productViewModel.getSimilarProducts(
                productId = product.id
            )
        }
    }

    if (productState.value.status) {
        StandardScaffold(
            topAppBar = {
                Header(
                    isFavorite = productState.value.favoriteProduct != null,
                    onNavigateToHome = onNavigateToHome
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Body(
                    cartViewModel = cartViewModel,
                    productState = productState,
                    onNavigateToDetails = onNavigateToDetails
                )

                FooterSection(
                    productState = productState,
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}

@Composable
private fun Header(
    isFavorite: Boolean,
    onNavigateToHome: () -> Unit
) {
    ProductTopBar(
        isFavorite = isFavorite,
        onNavigateToHome = onNavigateToHome
    )
}

@Composable
private fun Body(
    cartViewModel: CartViewModel,
    productState: State<ProductState>,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val product: Product? = productState.value.product
    val similarProductList: List<Product> = productState.value.similarProductList

    if (product != null) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ImageSection(
                product = product
            )

            Spacer(modifier = Modifier.height(10.dp))

            InfoSection(
                product = product
            )

            if (product.categories.isNotEmpty()) {
                Spacer(modifier = Modifier.height(18.dp))

                CategorySection(
                    categoryList = product.categories
                )
            }


            if (similarProductList.isNotEmpty()) {
                Spacer(modifier = Modifier.height(26.dp))

                SimilarProduct(
                    cartViewModel = cartViewModel,
                    similarProductList = similarProductList,
                    onNavigateToDetails = onNavigateToDetails
                )
            }

            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}

@Composable
private fun ImageSection(
    product: Product
) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        model = product.imageUrl,
        contentDescription = product.name,
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun InfoSection(
    product: Product
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        /* Title with price */
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = capitalizeText(product.name),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = formatNumber(product.price),
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        /* Description */
        product.description?.let { validateDescription ->
            Text(
                modifier = Modifier.alpha(0.8f),
                text = validateDescription,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.outline,
                maxLines = 6
            )
        }
    }
}

@Composable
private fun CategorySection(
    categoryList: List<Category>
) {
    val categoryNames: List<String> = categoryList.map { category -> category.name }

    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        StandardFlowRow(
            itemList = categoryNames,
            selectedItem = "",
            onItemSelected = {}
        )
    }
}

@Composable
private fun SimilarProduct(
    cartViewModel: CartViewModel,
    similarProductList: List<Product>,
    onNavigateToDetails: (productId: String) -> Unit
) {
    val cartState = cartViewModel.state.collectAsState()

    val cartItemList: List<CartItem> = cartState.value.cartItemList

    val similarProductCardList: List<CardItem> = similarProductList.map { it.toCardItem() }

    val cardItemList: MutableList<CardItem> = mutableListOf()
    cardItemList.addAll(similarProductCardList)

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        text = "Similares",
        style = MaterialTheme.typography.titleMedium
    )

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

@Composable
private fun FooterSection(
    cartViewModel: CartViewModel,
    productState: State<ProductState>
) {
    val cartState = cartViewModel.state.collectAsState()

    val cartItemList: List<CartItem> = cartState.value.cartItemList

    val product: Product? = productState.value.product

    val isOnTheCart = cartItemList.indexOfFirst { it.id == product?.id }

    val buttonContainerColor = if (isOnTheCart != -1)
        MaterialTheme.colorScheme.error else
        MaterialTheme.colorScheme.primary

    val buttonText = if (isOnTheCart != -1)
        "Remover" else
        "Agregar"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonContainerColor,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = {
                if (product != null) {
                    if (isOnTheCart != -1) {
                        cartViewModel.onEvent(
                            CartEvent.RemoveFromCart(product.toCartItem())
                        )
                    } else {
                        cartViewModel.onEvent(
                            CartEvent.AddToCart(product.toCartItem())
                        )
                    }
                }
            }
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = buttonText,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}