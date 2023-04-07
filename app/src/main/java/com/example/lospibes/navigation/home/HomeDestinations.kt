package com.example.lospibes.navigation.home

sealed class HomeDestinations(val route: String) {
    object AccountScreen : HomeDestinations(route = "account_screen")
    object FavoriteScreen : HomeDestinations(route = "favorite_screen")
    object HomeScreen : HomeDestinations(route = "home_screen")
    object OrderScreen : HomeDestinations(route = "order_screen")
}
