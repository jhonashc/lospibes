package com.example.lospibes.navigation.detail

sealed class DetailDestinations(val route: String) {
    object DetailScreen : DetailDestinations(route = "detail_screen")
}
