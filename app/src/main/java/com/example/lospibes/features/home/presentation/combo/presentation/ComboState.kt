package com.example.lospibes.features.home.presentation.combo.presentation

import com.example.lospibes.features.home.domain.model.Combo

data class ComboState(
    val message: String? = null,
    val isComboLoading: Boolean = true,
    val isFavoriteComboLoading: Boolean = true,
    val combo: Combo? = null,
    val favoriteCombo: Combo? = null,
)
