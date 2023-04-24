package com.example.lospibes.features.home.presentation.explore.presentation

sealed class ExploreEvent {
    data class SelectedCategory(val value: String) : ExploreEvent()
}
