package com.example.lospibes.features.home.presentation.combo.presentation

import com.example.lospibes.features.home.domain.model.Combo

data class ComboState(
    val message: String? = null,
    val isComboLoading: Boolean = true,
    val isSimilarComboLoading: Boolean = true,
    val combo: Combo? = null,
    val similarComboList: List<Combo> = emptyList()
)
