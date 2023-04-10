package com.example.lospibes.common.components

import androidx.compose.runtime.Composable
import com.example.lospibes.common.domain.model.CardItem
import com.example.lospibes.common.domain.model.Combo

@Composable
fun ComboCard(
    combo: Combo,
    isFavorite: Boolean = false,
    onClick: () -> Unit
) {
    StandardCard(
        cardItem = CardItem(
            name = combo.name,
            description = combo.description,
            imageUrl = combo.imageUrl,
            price = combo.price
        ),
        isFavorite = isFavorite,
        onClick = onClick
    )
}