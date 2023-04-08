package com.example.lospibes.navigation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lospibes.modules.home.account.presentation.AccountScreen
import com.example.lospibes.modules.home.favorite.presentation.FavoriteScreen
import com.example.lospibes.modules.home.home.presentation.HomeScreen
import com.example.lospibes.modules.home.order.presentation.OrderScreen
import com.example.lospibes.navigation.home.screens.ScreensDestinations
import com.example.lospibes.navigation.home.screens.screensNavGraph
import com.example.lospibes.utils.Constants.HOME_GRAPH_ROUTE
import com.example.lospibes.utils.Constants.HOME_SCREENS_GRAPH_ROUTE

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
            route = HomeDestinations.OrderScreen.route
        ) {
            OrderScreen()
        }

        composable(
            route = HomeDestinations.FavoriteScreen.route
        ) {
            FavoriteScreen()
        }

        composable(
            route = HomeDestinations.HomeScreen.route
        ) {
            HomeScreen(
                onNavigateToDetail = {
                    navController.navigate(ScreensDestinations.DetailScreen.route)
                },
                onNavigateToSearch = { query ->
                    navController.navigate("${ScreensDestinations.SearchScreen.route}?q=${query}")
                }
            )
        }

        screensNavGraph(navController = navController)
    }
}