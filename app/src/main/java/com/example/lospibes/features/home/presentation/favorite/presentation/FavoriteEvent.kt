package com.example.lospibes.features.home.presentation.favorite.presentation

sealed class FavoriteEvent {
    data class EnteredSearchBarText(val value: String) : FavoriteEvent()
    object OnSearchBarClick : FavoriteEvent()
    object OnSearchBarClose : FavoriteEvent()
}
