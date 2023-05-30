package com.example.lospibes.features.home.presentation.home.presentation

sealed class HomeEvent {
    object OnOpenBottomSheet : HomeEvent()
    object OnHideBottomSheet : HomeEvent()
}
