package com.example.lospibes.navigation.home.screens

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.lospibes.modules.home.details.presentation.DetailsScreen
import com.example.lospibes.modules.home.search.SearchScreen
import com.example.lospibes.utils.Constants.HOME_SCREENS_GRAPH_ROUTE

fun NavGraphBuilder.screensNavGraph(navController: NavHostController) {
    navigation(
        route = HOME_SCREENS_GRAPH_ROUTE,
        startDestination = ScreensDestinations.DetailsScreen.route
    ) {
        composable(
            route = "${ScreensDestinations.DetailsScreen.route}/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    defaultValue = ""
                    type = NavType.StringType
                }
            )
        ) {
            DetailsScreen(
                onNavigateToHome = {
                    navController.popBackStack()
                },
                onNavigateToProductDetails = { productId ->
                    navController.navigate("${ScreensDestinations.DetailsScreen.route}/${productId}")
                }
            )
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
            SearchScreen(
                onNavigateToHome = {
                    navController.popBackStack()
                }
            )
        }
    }
}