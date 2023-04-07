package com.example.lospibes.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lospibes.modules.auth.presentation.login.LoginScreen
import com.example.lospibes.modules.auth.presentation.register.RegisterScreen
import com.example.lospibes.utils.Constants.AUTH_GRAPH_ROUTE

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        route = AUTH_GRAPH_ROUTE,
        startDestination = AuthDestinations.LoginScreen.route,
    ) {
        composable(
            route = AuthDestinations.LoginScreen.route
        ) {
            LoginScreen()
        }
        composable(
            route = AuthDestinations.RegisterScreen.route
        ) {
            RegisterScreen()
        }
    }
}