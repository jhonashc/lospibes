package com.example.lospibes.navigation.home

sealed class HomeDestinations(val route: String) {
    object FavoriteScreen : HomeDestinations(route = "favorite_screen")
    object HomeScreen : HomeDestinations(route = "home_screen")
    object ProfileScreen : HomeDestinations(route = "account_screen")
    object SearchScreen : HomeDestinations(route = "search_screen")
    object CartScreen : HomeDestinations(route = "cart_screen")
}
