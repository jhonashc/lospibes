package com.example.lospibes.features.home.component

import androidx.compose.runtime.Composable
import com.example.lospibes.core.component.StandardCardListGrid
import com.example.lospibes.core.component.StandardCardListRow
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.features.home.domain.model.Combo

@Composable
fun ComboListRow(
    combos: List<Combo>,
    favoriteCombos: List<Combo> = listOf(),
    onComboSelected: (combo: Combo) -> Unit
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

    val favoriteCardItemList: List<CardItem> = favoriteCombos.map { combo ->
        CardItem(
            id = combo.id,
            name = combo.name,
            description = combo.description,
            imageUrl = combo.imageUrl,
            price = combo.price
        )
    }

    val getSelectedComboById = { comboId: String ->
        combos.find { combo -> combo.id == comboId }
    }

    StandardCardListRow(
        cardItemList = cardItemList,
        favoriteCardItemList = favoriteCardItemList,
        onCardItemSelected = { selectedCardItem ->
            val selectedCombo: Combo? = getSelectedComboById(selectedCardItem.id)
            selectedCombo?.let(onComboSelected)
        }
    )
}

@Composable
fun ComboListGrid(
    combos: List<Combo>,
    favoriteCombos: List<Combo> = listOf(),
    onComboSelected: (combo: Combo) -> Unit
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

    val favoriteCardItemList: List<CardItem> = favoriteCombos.map { combo ->
        CardItem(
            id = combo.id,
            name = combo.name,
            description = combo.description,
            imageUrl = combo.imageUrl,
            price = combo.price
        )
    }

    val getSelectedComboById = { comboId: String ->
        combos.find { combo -> combo.id == comboId }
    }

    StandardCardListGrid(
        cardItemList = cardItemList,
        favoriteCardItemList = favoriteCardItemList,
        onCardItemSelected = { selectedCardItem ->
            val selectedCombo: Combo? = getSelectedComboById(selectedCardItem.id)
            selectedCombo?.let(onComboSelected)
        }
    )
}