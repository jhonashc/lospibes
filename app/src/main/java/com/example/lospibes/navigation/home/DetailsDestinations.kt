package com.example.lospibes.navigation.home

sealed class DetailsDestinations(val route: String) {
    object ProductDetailsScreen : DetailsDestinations(route = "product_details_screen")
    object ExploreFilterScreen : DetailsDestinations(route = "explore_filter_screen")
}
