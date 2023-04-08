package com.example.lospibes.navigation.home.screens


sealed class ScreensDestinations(val route: String) {
    object DetailsScreen : ScreensDestinations(route = "detail_screen")
    object SearchScreen : ScreensDestinations(route = "search_screen")
}
