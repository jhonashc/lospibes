package com.example.lospibes.navigation.home

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lospibes.features.home.presentation.cart.presentation.CartScreen
import com.example.lospibes.features.home.presentation.combo.presentation.ComboScreen
import com.example.lospibes.features.home.presentation.filter.presentation.FilterScreen
import com.example.lospibes.features.home.presentation.product.presentation.ProductScreen
import com.example.lospibes.features.home.presentation.explore.presentation.ExploreScreen
import com.example.lospibes.features.home.presentation.favorite.presentation.FavoriteScreen
import com.example.lospibes.features.home.presentation.home.presentation.HomeScreen
import com.example.lospibes.features.home.presentation.profile.presentation.ProfileScreen
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
            route = HomeDestinations.ProfileScreen.route
        ) {
            ProfileScreen()
        }

        composable(
            route = HomeDestinations.CartScreen.route
        ) {
            CartScreen(
                onNavigateToHome = {
                    navController.popBackStack(
                        route = HomeDestinations.HomeScreen.route,
                        inclusive = false
                    )
                }
            )
        }

        composable(
            route = HomeDestinations.FavoriteScreen.route
        ) {
            FavoriteScreen(
                onNavigateToHome = {
                    navController.popBackStack(
                        route = HomeDestinations.HomeScreen.route,
                        inclusive = false
                    )
                },
                onNavigateToDetails = { isCombo, id ->
                    if (isCombo) {
                        navController.navigate("${DetailsDestinations.ComboDetailsScreen.route}/${id}")
                    } else {
                        navController.navigate("${DetailsDestinations.ProductDetailsScreen.route}/${id}")
                    }
                }
            )
        }

        composable(
            route = HomeDestinations.HomeScreen.route
        ) {
            HomeScreen(
                onNavigateToExplore = { query ->
                    navController.navigate("${HomeDestinations.ExploreScreen.route}?q=${query}")
                },
                onNavigateToDetails = { isCombo, id ->
                    if (isCombo) {
                        navController.navigate("${DetailsDestinations.ComboDetailsScreen.route}/${id}")
                    } else {
                        navController.navigate("${DetailsDestinations.ProductDetailsScreen.route}/${id}")
                    }
                }
            )
        }

        composable(
            route = HomeDestinations.ExploreScreen.route,
            arguments = listOf(
                navArgument("q") {
                    defaultValue = ""
                    type = NavType.StringType
                }
            )
        ) {
            ExploreScreen(
                onNavigateToHome = {
                    navController.popBackStack(
                        route = HomeDestinations.HomeScreen.route,
                        inclusive = false
                    )
                },
                onNavigateToFilter = {
                    navController.navigate(DetailsDestinations.ExploreFilterScreen.route)
                },
                onNavigateToDetails = { isCombo, id ->
                    if (isCombo) {
                        navController.navigate("${DetailsDestinations.ComboDetailsScreen.route}/${id}")
                    } else {
                        navController.navigate("${DetailsDestinations.ProductDetailsScreen.route}/${id}")
                    }
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
            ComboScreen(
                onNavigateToHome = {
                    navController.popBackStack(
                        route = HomeDestinations.HomeScreen.route,
                        inclusive = false
                    )
                },
                onNavigateToDetails = { isCombo, id ->
                    if (isCombo) {
                        navController.navigate("${DetailsDestinations.ComboDetailsScreen.route}/${id}")
                    }
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
            ProductScreen(
                onNavigateToHome = {
                    navController.popBackStack(
                        route = HomeDestinations.HomeScreen.route,
                        inclusive = false
                    )
                },
                onNavigateToDetails = { isCombo, id ->
                    if (!isCombo) {
                        navController.navigate("${DetailsDestinations.ProductDetailsScreen.route}/${id}")
                    }
                }
            )
        }

        composable(
            route = DetailsDestinations.ExploreFilterScreen.route
        ) {
            FilterScreen(
                onNavigateToExplore = {
                    navController.popBackStack()
                }
            )
        }
    }
}