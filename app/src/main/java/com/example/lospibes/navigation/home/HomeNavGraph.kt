package com.example.lospibes.navigation.home

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lospibes.modules.home.account.presentation.AccountScreen
import com.example.lospibes.modules.home.details.presentation.DetailsScreen
import com.example.lospibes.modules.home.favorite.presentation.FavoriteScreen
import com.example.lospibes.modules.home.home.presentation.HomeScreen
import com.example.lospibes.modules.home.order.presentation.OrderScreen
import com.example.lospibes.modules.home.search.SearchScreen
import com.example.lospibes.utils.Constants.DETAIL_GRAPH_ROUTE
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
            route = HomeDestinations.OrderScreen.route
        ) {
            OrderScreen()
        }

        composable(
            route = HomeDestinations.FavoriteScreen.route
        ) {
            FavoriteScreen(
                onNavigateToProductDetails = { productId ->
                    navController.navigate("${DetailsDestinations.ProductDetailsScreen.route}/${productId}")
                }
            )
        }

        composable(
            route = HomeDestinations.HomeScreen.route
        ) {
            HomeScreen(
                onNavigateToProductDetails = { productId ->
                    navController.navigate("${DetailsDestinations.ProductDetailsScreen.route}/${productId}")
                },
                onNavigateToSearch = { query ->
                    navController.navigate("${DetailsDestinations.SearchScreen.route}?q=${query}")
                }
            )
        }

        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = DETAIL_GRAPH_ROUTE,
        startDestination = DetailsDestinations.ProductDetailsScreen.route
    ) {
        composable(
            route = "${DetailsDestinations.ProductDetailsScreen.route}/{productId}",
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
                    navController.navigate("${DetailsDestinations.ProductDetailsScreen.route}/${productId}")
                }
            )
        }

        composable(
            route = "${DetailsDestinations.SearchScreen.route}?q={q}",
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