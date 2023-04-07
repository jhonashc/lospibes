package com.example.lospibes.navigation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lospibes.modules.home.account.presentation.AccountScreen
import com.example.lospibes.modules.home.cart.presentation.CartScreen
import com.example.lospibes.modules.home.explore.presentation.ExploreScreen
import com.example.lospibes.modules.home.home.presentation.HomeScreen
import com.example.lospibes.utils.Constants.HOME_GRAPH_ROUTE

@Composable
fun HomeNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestinations.HomeScreen.route,
        route = HOME_GRAPH_ROUTE
    ) {
        composable(
            route = HomeDestinations.AccountScreen.route
        ) {
            AccountScreen()
        }

        composable(
            route = HomeDestinations.CartScreen.route
        ) {
            CartScreen()
        }

        composable(
            route = HomeDestinations.ExploreScreen.route
        ) {
            ExploreScreen()
        }

        composable(
            route = HomeDestinations.HomeScreen.route
        ) {
            HomeScreen()
        }
    }
}