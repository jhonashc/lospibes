package com.example.lospibes.features.home.presentation.home.presentation

import com.example.lospibes.features.home.domain.model.TabItem

sealed class HomeEvent {
    data class EnteredCategory(val value: TabItem) : HomeEvent()
    object OnOpenBottomSheet : HomeEvent()
    object OnHideBottomSheet : HomeEvent()
}
