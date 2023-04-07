package com.example.lospibes.navigation.auth

sealed class AuthDestinations(val route: String) {
    object LoginScreen : AuthDestinations(route = "login_screen")
    object RegisterScreen : AuthDestinations(route = "register_screen")
}
