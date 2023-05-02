package com.example.lospibes.navigation.home

sealed class HomeDestinations(val route: String) {
    object FavoriteScreen : HomeDestinations(route = "favorite_screen")
    object HomeScreen : HomeDestinations(route = "home_screen")
    object ProfileScreen : HomeDestinations(route = "account_screen")
    object ExploreScreen : HomeDestinations(
        route = "explore_screen?name={name}&category={category}&min={min}&max={max}"
    )
    object CartScreen : HomeDestinations(route = "cart_screen")
}
