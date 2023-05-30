package com.example.lospibes.features.home.presentation.explore.presentation

sealed class ExploreBottomEvent {
    data class EnteredCategory(val value: String) : ExploreBottomEvent()
    data class EnteredRange(val value: ClosedFloatingPointRange<Float>) : ExploreBottomEvent()
    object OnResetQuery : ExploreBottomEvent()
}
