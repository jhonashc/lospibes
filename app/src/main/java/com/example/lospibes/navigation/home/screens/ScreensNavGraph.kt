package com.example.lospibes.navigation.home.screens

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.lospibes.modules.home.detail.presentation.DetailScreen
import com.example.lospibes.modules.home.search.SearchScreen
import com.example.lospibes.utils.Constants.HOME_SCREENS_GRAPH_ROUTE

fun NavGraphBuilder.screensNavGraph(navController: NavHostController) {
    navigation(
        route = HOME_SCREENS_GRAPH_ROUTE,
        startDestination = ScreensDestinations.DetailScreen.route
    ) {
        composable(
            route = ScreensDestinations.DetailScreen.route
        ) {
            DetailScreen(productId = "1")
        }

        composable(
            route = "${ScreensDestinations.SearchScreen.route}?q={q}",
            arguments = listOf(
                navArgument("q") {
                    defaultValue = ""
                    type = NavType.StringType
                }
            )
        ) {
            SearchScreen()
        }
    }
}