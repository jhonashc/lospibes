package com.example.lospibes.navigation.home

sealed class HomeDestinations(val route: String) {
    object AccountScreen : HomeDestinations(route = "account_screen")
    object CartScreen : HomeDestinations(route = "cart_screen")
    object ExploreScreen : HomeDestinations(route = "explore_screen")
    object HomeScreen : HomeDestinations(route = "home_screen")
}
