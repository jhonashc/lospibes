package com.example.lospibes.core.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.features.home.domain.model.CartItem

@Composable
fun StandardCardListRow(
    cardItemList: List<CardItem>,
    favoriteCardItemList: List<CardItem> = listOf(),
    cartItemList: List<CartItem> = listOf(),
    showFavIcon: Boolean = false,
    onCardItemSelected: (selectedCardItem: CardItem) -> Unit,
    onAddOrRemoveClick: (selectedCardItem: CardItem) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        itemsIndexed(cardItemList) { index, cardItem ->
            Row(
                modifier = Modifier.padding(
                    start = if (index == 0) 20.dp else 0.dp,
                    end = if (cardItem.id == cardItemList.last().id) 20.dp else 0.dp
                )
            ) {
                val isOnTheCart = cartItemList.indexOfFirst { it.id == cardItem.id }

                StandardCard(
                    cardItem = cardItem,
                    isFavorite = favoriteCardItemList.contains(cardItem),
                    isOnTheCart = isOnTheCart != -1,
                    showFavIcon = showFavIcon,
                    onCardClick = { onCardItemSelected(cardItem) },
                    onAddOrRemoveClick = onAddOrRemoveClick
                )
            }
        }
    }
}

@Composable
fun StandardCardListGrid(
    columns: Int = 2,
    cardItemList: List<CardItem>,
    cartItemList: List<CartItem> = listOf(),
    favoriteCardItemList: List<CardItem> = listOf(),
    showFavIcon: Boolean = false,
    onCardItemSelected: (listItem: CardItem) -> Unit,
    onAddOrRemoveClick: (selectedCardItem: CardItem) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(20.dp)
    ) {
        items(cardItemList) { cardItem ->
            val isOnTheCart = cartItemList.indexOfFirst { it.id == cardItem.id }

            StandardCard(
                cardItem = cardItem,
                isFavorite = favoriteCardItemList.contains(cardItem),
                isOnTheCart = isOnTheCart != -1,
                showFavIcon = showFavIcon,
                onCardClick = { onCardItemSelected(cardItem) },
                onAddOrRemoveClick = onAddOrRemoveClick
            )
        }
    }
}