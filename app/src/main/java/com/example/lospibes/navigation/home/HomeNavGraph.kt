package com.example.lospibes.navigation.home

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lospibes.features.home.account.presentation.AccountScreen
import com.example.lospibes.features.home.details.presentation.ComboDetailsScreen
import com.example.lospibes.features.home.details.presentation.ProductDetailsScreen
import com.example.lospibes.features.home.details.presentation.SearchScreen
import com.example.lospibes.features.home.favorite.presentation.FavoriteScreen
import com.example.lospibes.features.home.home.presentation.HomeScreen
import com.example.lospibes.features.home.order.presentation.OrderScreen
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
                onNavigateToComboDetails = { comboId ->
                    navController.navigate("${DetailsDestinations.ComboDetailsScreen.route}/${comboId}")
                },
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
            route = "${DetailsDestinations.ComboDetailsScreen.route}/{comboId}",
            arguments = listOf(
                navArgument("comboId") {
                    defaultValue = ""
                    type = NavType.StringType
                }
            )
        ) {
            ComboDetailsScreen(
                onNavigateToHome = {
                    navController.popBackStack()
                },
                onNavigateToComboDetails = { comboId ->
                    navController.navigate("${DetailsDestinations.ComboDetailsScreen.route}/${comboId}")
                }
            )
        }

        composable(
            route = "${DetailsDestinations.ProductDetailsScreen.route}/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    defaultValue = ""
                    type = NavType.StringType
                }
            )
        ) {
            ProductDetailsScreen(
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