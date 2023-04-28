package com.example.lospibes.features.home.component

import androidx.compose.runtime.Composable
import com.example.lospibes.core.component.StandardCardListGrid
import com.example.lospibes.core.component.StandardCardListRow
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.toCardItem

@Composable
fun ProductListRow(
    products: List<Product>,
    favoriteProducts: List<Product> = listOf(),
    cartItemList: List<CartItem> = listOf(),
    onProductSelected: (selectedProduct: Product) -> Unit,
    onAddOrRemoveClick: (selectedCardItem: CardItem) -> Unit = {}
) {
    val cardItemList: List<CardItem> = products.map { it.toCardItem() }

    val favoriteCardItemList: List<CardItem> = favoriteProducts.map { it.toCardItem() }

    val getSelectedProductById = { productId: String ->
        products.find { product -> product.id == productId }
    }

    StandardCardListRow(
        cardItemList = cardItemList,
        favoriteCardItemList = favoriteCardItemList,
        cartItemList = cartItemList,
        onCardItemSelected = { selectedCardItem ->
            val selectedProduct: Product? = getSelectedProductById(selectedCardItem.id)
            selectedProduct?.let(onProductSelected)
        },
        onAddOrRemoveClick = onAddOrRemoveClick
    )
}

@Composable
fun ProductListGrid(
    products: List<Product>,
    favoriteProducts: List<Product> = listOf(),
    cartItemList: List<CartItem> = listOf(),
    onProductSelected: (product: Product) -> Unit,
    onAddOrRemoveClick: (selectedCardItem: CardItem) -> Unit = {}
) {
    val cardItemList: List<CardItem> = products.map { it.toCardItem() }

    val favoriteCardItemList: List<CardItem> = favoriteProducts.map { it.toCardItem() }

    val getSelectedProductById = { productId: String ->
        products.find { product -> product.id == productId }
    }

    StandardCardListGrid(
        cardItemList = cardItemList,
        favoriteCardItemList = favoriteCardItemList,
        cartItemList = cartItemList,
        onCardItemSelected = { selectedCardItem ->
            val selectedProduct: Product? = getSelectedProductById(selectedCardItem.id)
            selectedProduct?.let(onProductSelected)
        },
        onAddOrRemoveClick = onAddOrRemoveClick
    )
}