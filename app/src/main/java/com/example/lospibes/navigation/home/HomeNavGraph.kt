package com.example.lospibes.navigation.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.example.lospibes.features.home.viewmodel.cart.CartViewModel
import com.example.lospibes.utils.Constants.DETAIL_GRAPH_ROUTE
import com.example.lospibes.utils.Constants.HOME_GRAPH_ROUTE

@Composable
fun HomeNavGraph(
    navController: NavHostController
) {
    val cartViewModel: CartViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = HomeDestinations.HomeScreen.route,
        route = HOME_GRAPH_ROUTE
    ) {
        composable(
            route = HomeDestinations.ProfileScreen.route
        ) {
            ProfileScreen(
                onNavigateToHome = {}
            )
        }

        composable(
            route = HomeDestinations.CartScreen.route
        ) {
            CartScreen(
                cartViewModel = cartViewModel,
                onNavigateToHome = {}
            )
        }

        composable(
            route = HomeDestinations.FavoriteScreen.route
        ) {
            FavoriteScreen(
                cartViewModel = cartViewModel,
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
                cartViewModel = cartViewModel,
                onNavigateToExplore = { category ->
                    navController.navigate("${HomeDestinations.ExploreScreen.route}?category=${category}") {
                        popUpTo(HomeDestinations.HomeScreen.route) {
                            inclusive = true
                        }
                    }
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
            route = "${HomeDestinations.ExploreScreen.route}?name={name}&category={category}&min={min}&max={max}",
            arguments = listOf(
                navArgument("name") {
                    defaultValue = ""
                    type = NavType.StringType
                },
                navArgument("category") {
                    defaultValue = ""
                    type = NavType.StringType
                },
                navArgument("min") {
                    defaultValue = 0
                    type = NavType.IntType
                },
                navArgument("max") {
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {
            ExploreScreen(
                onNavigateToHome = {},
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

        detailsNavGraph(
            navController = navController,
            cartViewModel = cartViewModel
        )
    }
}

fun NavGraphBuilder.detailsNavGraph(
    navController: NavHostController,
    cartViewModel: CartViewModel
) {
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
                cartViewModel = cartViewModel,
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
                cartViewModel = cartViewModel,
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