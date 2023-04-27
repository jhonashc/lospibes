package com.example.lospibes.features.home.component

import androidx.compose.runtime.Composable
import com.example.lospibes.core.component.StandardCardListGrid
import com.example.lospibes.core.component.StandardCardListRow
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.model.Combo
import com.example.lospibes.features.home.domain.model.toCardItem

@Composable
fun ComboListRow(
    combos: List<Combo>,
    favoriteCombos: List<Combo> = listOf(),
    cartItemList: List<CartItem> = listOf(),
    onComboSelected: (combo: Combo) -> Unit,
    onAddOrRemoveClick: (selectedCardItem: CardItem) -> Unit = {}
) {
    val cardItemList: List<CardItem> = combos.map { it.toCardItem() }

    val favoriteCardItemList: List<CardItem> = favoriteCombos.map { it.toCardItem() }

    val getSelectedComboById = { comboId: String ->
        combos.find { combo -> combo.id == comboId }
    }

    StandardCardListRow(
        cardItemList = cardItemList,
        favoriteCardItemList = favoriteCardItemList,
        cartItemList = cartItemList,
        onCardItemSelected = { selectedCardItem ->
            val selectedCombo: Combo? = getSelectedComboById(selectedCardItem.id)
            selectedCombo?.let(onComboSelected)
        },
        onAddOrRemoveClick = onAddOrRemoveClick
    )
}

@Composable
fun ComboListGrid(
    combos: List<Combo>,
    favoriteCombos: List<Combo> = listOf(),
    cartItemList: List<CartItem> = listOf(),
    onComboSelected: (combo: Combo) -> Unit,
    onAddOrRemoveClick: (selectedCardItem: CardItem) -> Unit = {}
) {
    val cardItemList: List<CardItem> = combos.map { it.toCardItem() }

    val favoriteCardItemList: List<CardItem> = favoriteCombos.map { it.toCardItem() }

    val getSelectedComboById = { comboId: String ->
        combos.find { combo -> combo.id == comboId }
    }

    StandardCardListGrid(
        cardItemList = cardItemList,
        favoriteCardItemList = favoriteCardItemList,
        cartItemList = cartItemList,
        onCardItemSelected = { selectedCardItem ->
            val selectedCombo: Combo? = getSelectedComboById(selectedCardItem.id)
            selectedCombo?.let(onComboSelected)
        },
        onAddOrRemoveClick = onAddOrRemoveClick
    )
}