package com.example.lospibes.core.components

import androidx.compose.runtime.Composable
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.features.home.domain.model.Combo

@Composable
fun ComboListRow(
    combos: List<Combo>,
    favoriteCombos: List<Combo> = listOf(),
    onSelectCombo: (combo: Combo) -> Unit
) {
    val cardItemList: List<CardItem> = combos.map { combo ->
        CardItem(
            id = combo.id,
            name = combo.name,
            description = combo.description,
            imageUrl = combo.imageUrl,
            price = combo.price
        )
    }

    val favoriteCardItemList: List<CardItem> = favoriteCombos.map { product ->
        CardItem(
            id = product.id,
            name = product.name,
            description = product.description,
            imageUrl = product.imageUrl,
            price = product.price
        )
    }

    val getSelectedComboById = { comboId: String ->
        combos.find { combo -> combo.id == comboId }
    }

    StandardCardListRow(
        cardItemList = cardItemList,
        favoriteCardItemList = favoriteCardItemList,
        onSelectCardItem = { selectedCardItem ->
            val selectedCombo: Combo? = getSelectedComboById(selectedCardItem.id)
            selectedCombo?.let(onSelectCombo)
        }
    )
}