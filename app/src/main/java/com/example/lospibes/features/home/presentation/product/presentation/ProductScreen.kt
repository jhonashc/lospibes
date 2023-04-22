package com.example.lospibes.features.home.presentation.product.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.ArrowBack
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
import com.example.lospibes.core.component.StandardBoxContainer
import com.example.lospibes.core.component.StandardFlowRow
import com.example.lospibes.core.component.StandardTopBar
import com.example.lospibes.features.home.component.ProductListRow
import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
import com.example.lospibes.features.home.domain.model.Category
import com.example.lospibes.features.home.domain.model.Product

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.value.product) {
        val categories: List<Category> = state.value.product?.categories ?: emptyList()

        if (categories.isNotEmpty()) {
            viewModel.getSimilarProducts(
                getProductsQueryDto = GetProductsQueryDto(
                    category = categories.first().name
                )
            )
        }
    }

    StandardBoxContainer(
        isLoading = state.value.isProductLoading &&
                state.value.isSimilarProductLoading,
        message = state.value.message
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Header(
                isFavorite = false,
                onNavigateToHome = onNavigateToHome
            )

            Body(
                state = state,
                onNavigateToDetails = onNavigateToDetails
            )

            FooterSection()
        }
    }
}

@Composable
private fun Header(
    isFavorite: Boolean,
    onNavigateToHome: () -> Unit
) {
    val favoriteIcon = if (isFavorite)
        Icons.Filled.Favorite else
        Icons.Filled.FavoriteBorder

    StandardTopBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Back Icon"
            )
        },
        actions = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                onClick = { /* TODO */ }
            ) {
                Icon(
                    imageVector = favoriteIcon,
                    contentDescription = "Favorite Icon"
                )
            }
        },
        onBackTo = onNavigateToHome
    )
}

@Composable
private fun Body(
    state: State<ProductState>,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val product: Product? = state.value.product

    product?.let { validateProduct ->
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ImageSection(
                product = validateProduct
            )

            Spacer(modifier = Modifier.height(10.dp))

            InfoSection(
                product = validateProduct
            )

            Spacer(modifier = Modifier.height(26.dp))

            CategorySection(
                categoryList = validateProduct.categories
            )

            Spacer(modifier = Modifier.height(26.dp))

            SimilarSection(
                state = state,
                onNavigateToDetails = onNavigateToDetails
            )

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
                text = product.name,
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
                    text = "${product.price}",
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
        Text(
            text = "Categorías",
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(18.dp))

        StandardFlowRow(
            itemList = categoryNames,
            selectedItem = "",
            onItemSelected = {}
        )
    }
}

@Composable
private fun SimilarSection(
    state: State<ProductState>,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val similarProductList: List<Product> = state.value.similarProductList

    Text(
        modifier = Modifier.padding(horizontal = 20.dp),
        text = "Similares \uD83D\uDCA3️",
        style = MaterialTheme.typography.titleMedium,
    )

    Spacer(modifier = Modifier.height(22.dp))

    ProductListRow(
        products = similarProductList,
        onProductSelected = { selectedProduct ->
            onNavigateToDetails(false, selectedProduct.id)
        }
    )
}

@Composable
private fun FooterSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = { /*TODO*/ }
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Agregar",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}