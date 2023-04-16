package com.example.lospibes.core.components

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

@Composable
fun StandardCardListRow(
    cardItemList: List<CardItem>,
    favoriteCardItemList: List<CardItem> = listOf(),
    onCardItemSelected: (selectedCardItem: CardItem) -> Unit
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
                StandardCard(
                    cardItem = CardItem(
                        id = cardItem.id,
                        name = cardItem.name,
                        description = cardItem.description,
                        imageUrl = cardItem.imageUrl,
                        price = cardItem.price,
                    ),
                    isFavorite = favoriteCardItemList.contains(cardItem),
                    onClick = { onCardItemSelected(cardItem) }
                )
            }
        }
    }
}

@Composable
fun StandardCardListGrid(
    columns: Int = 2,
    cardItemList: List<CardItem>,
    favoriteCardItemList: List<CardItem> = listOf(),
    onCardItemSelected: (listItem: CardItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(20.dp)
    ) {
        items(cardItemList) { cardItem ->
            StandardCard(
                cardItem = CardItem(
                    id = cardItem.id,
                    name = cardItem.name,
                    description = cardItem.description,
                    imageUrl = cardItem.imageUrl,
                    price = cardItem.price,
                ),
                isFavorite = favoriteCardItemList.contains(cardItem),
                onClick = { onCardItemSelected(cardItem) }
            )
        }
    }
}