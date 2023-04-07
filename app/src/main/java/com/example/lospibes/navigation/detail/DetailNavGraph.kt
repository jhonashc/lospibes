package com.example.lospibes.navigation.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lospibes.modules.home.detail.presentation.DetailScreen
import com.example.lospibes.utils.Constants.DETAIL_GRAPH_ROUTE

fun NavGraphBuilder.detailNavGraph(navController: NavHostController) {
    navigation(
        route = DETAIL_GRAPH_ROUTE,
        startDestination = DetailDestinations.DetailScreen.route
    ) {
        composable(
            route = DetailDestinations.DetailScreen.route
        ) {
            DetailScreen(productId = "1")
        }
    }
}