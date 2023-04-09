package com.example.lospibes.navigation.home

sealed class DetailsDestinations(val route: String) {
    object ProductDetailsScreen : DetailsDestinations(route = "details_screen")
    object SearchScreen : DetailsDestinations(route = "search_screen")
}
