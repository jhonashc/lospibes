package com.example.lospibes.features.home.presentation.explore.presentation

sealed class ExploreEvent {
    data class EnteredSearchBarText(val value: String) : ExploreEvent()
    object OnSearchBarClick : ExploreEvent()
    object OnSearchBarClose : ExploreEvent()
}
