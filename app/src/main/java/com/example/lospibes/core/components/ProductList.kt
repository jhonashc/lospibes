package com.example.lospibes.core.components

import androidx.compose.runtime.Composable
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.features.home.domain.model.Product

@Composable
fun ProductListRow(
    products: List<Product>,
    favoriteProducts: List<Product> = listOf(),
    onSelectProduct: (product: Product) -> Unit
) {
    val cardItemList: List<CardItem> = products.map { product ->
        CardItem(
            id = product.id,
            name = product.name,
            description = product.description,
            imageUrl = product.imageUrl,
            price = product.price
        )
    }

    val favoriteCardItemList: List<CardItem> = favoriteProducts.map { product ->
        CardItem(
            id = product.id,
            name = product.name,
            description = product.description,
            imageUrl = product.imageUrl,
            price = product.price
        )
    }

    val getSelectedProductById = { productId: String ->
        products.find { product -> product.id == productId }
    }

    StandardCardListRow(
        cardItemList = cardItemList,
        favoriteCardItemList = favoriteCardItemList,
        onSelectCardItem = { selectedCardItem ->
            val selectedProduct: Product? = getSelectedProductById(selectedCardItem.id)
            selectedProduct?.let(onSelectProduct)
        }
    )
}

@Composable
fun DetailedProductListColumn(
    products: List<Product>,
    favoriteProducts: List<Product> = listOf(),
    onSelectProduct: (product: Product) -> Unit
) {
    val cardItemList: List<CardItem> = products.map { product ->
        CardItem(
            id = product.id,
            name = product.name,
            description = product.description,
            imageUrl = product.imageUrl,
            price = product.price
        )
    }

    val favoriteCardItemList: List<CardItem> = favoriteProducts.map { product ->
        CardItem(
            id = product.id,
            name = product.name,
            description = product.description,
            imageUrl = product.imageUrl,
            price = product.price
        )
    }

    val getSelectedProductById = { productId: String ->
        products.find { product -> product.id == productId }
    }

    StandardCardDetailedListColumn(
        cardItemList = cardItemList,
        favoriteCardItemList = favoriteCardItemList,
        onSelectCardItem = { selectedCardItem ->
            val selectedProduct: Product? = getSelectedProductById(selectedCardItem.id)
            selectedProduct?.let(onSelectProduct)
        }
    )
}

@Composable
fun ProductListGrid(
    products: List<Product>,
    favoriteProducts: List<Product> = listOf(),
    onSelectProduct: (product: Product) -> Unit
) {
    val cardItemList: List<CardItem> = products.map { product ->
        CardItem(
            id = product.id,
            name = product.name,
            description = product.description,
            imageUrl = product.imageUrl,
            price = product.price
        )
    }

    val favoriteCardItemList: List<CardItem> = favoriteProducts.map { product ->
        CardItem(
            id = product.id,
            name = product.name,
            description = product.description,
            imageUrl = product.imageUrl,
            price = product.price
        )
    }

    val getSelectedProductById = { productId: String ->
        products.find { product -> product.id == productId }
    }

    StandardCardListGrid(
        cardItemList = cardItemList,
        favoriteCardItemList = favoriteCardItemList,
        onSelectCardItem = { selectedCardItem ->
            val selectedProduct: Product? = getSelectedProductById(selectedCardItem.id)
            selectedProduct?.let(onSelectProduct)
        }
    )
}