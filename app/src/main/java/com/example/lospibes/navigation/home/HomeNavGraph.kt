package com.example.lospibes.navigation.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lospibes.features.home.presentation.cart.presentation.CartScreen
import com.example.lospibes.features.home.presentation.filter.presentation.FilterScreen
import com.example.lospibes.features.home.presentation.product.presentation.ProductScreen
import com.example.lospibes.features.home.presentation.explore.presentation.ExploreScreen
import com.example.lospibes.features.home.presentation.favorite.presentation.FavoriteScreen
import com.example.lospibes.features.home.presentation.home.presentation.HomeScreen
import com.example.lospibes.features.home.presentation.profile.presentation.ProfileScreen
import com.example.lospibes.features.home.view_model.cart.CartViewModel
import com.example.lospibes.features.home.view_model.scaffold.ScaffoldEvent
import com.example.lospibes.features.home.view_model.scaffold.ScaffoldViewModel
import com.example.lospibes.utils.Constants.DETAIL_GRAPH_ROUTE
import com.example.lospibes.utils.Constants.HOME_GRAPH_ROUTE

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    scaffoldViewModel: ScaffoldViewModel
) {
    val cartViewModel: CartViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = HomeDestinations.HomeScreen.route,
        route = HOME_GRAPH_ROUTE
    ) {
        composable(
            route = HomeDestinations.HomeScreen.route
        ) {
            scaffoldViewModel.onEvent(ScaffoldEvent.ShowBottomBar)

            HomeScreen(
                cartViewModel = cartViewModel,
                onNavigateToExplore = { category ->
                    navController.navigate("explore_screen?category=${category}")
                },
                onNavigateToDetails = { productId ->
                    navController.navigate("${DetailsDestinations.ProductDetailsScreen.route}/${productId}")
                }
            )
        }

        composable(
            route = HomeDestinations.ExploreScreen.route,
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
            scaffoldViewModel.onEvent(ScaffoldEvent.ShowBottomBar)

            ExploreScreen(
                cartViewModel = cartViewModel,
                onNavigateToHome = {
                    navController.navigate(HomeDestinations.HomeScreen.route)
                },
                onNavigateToFilter = {
                    navController.navigate(DetailsDestinations.ExploreFilterScreen.route)
                },
                onNavigateToDetails = { productId ->
                    navController.navigate("${DetailsDestinations.ProductDetailsScreen.route}/${productId}")
                }
            )
        }

        composable(
            route = HomeDestinations.CartScreen.route
        ) {
            scaffoldViewModel.onEvent(ScaffoldEvent.ShowBottomBar)

            CartScreen(
                cartViewModel = cartViewModel,
                onNavigateToHome = {
                    navController.navigate(HomeDestinations.HomeScreen.route)
                }
            )
        }

        composable(
            route = HomeDestinations.FavoriteScreen.route
        ) {
            scaffoldViewModel.onEvent(ScaffoldEvent.ShowBottomBar)

            FavoriteScreen(
                cartViewModel = cartViewModel,
                onNavigateToHome = {},
                onNavigateToDetails = { productId ->
                    navController.navigate("${DetailsDestinations.ProductDetailsScreen.route}/${productId}")
                }
            )
        }

        composable(
            route = HomeDestinations.ProfileScreen.route
        ) {
            scaffoldViewModel.onEvent(ScaffoldEvent.ShowBottomBar)

            ProfileScreen(
                onNavigateToHome = {
                    navController.navigate(HomeDestinations.HomeScreen.route)
                }
            )
        }

        detailsNavGraph(
            navController = navController,
            cartViewModel = cartViewModel,
            scaffoldViewModel = scaffoldViewModel
        )
    }
}

fun NavGraphBuilder.detailsNavGraph(
    navController: NavHostController,
    cartViewModel: CartViewModel,
    scaffoldViewModel: ScaffoldViewModel
) {
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
            scaffoldViewModel.onEvent(ScaffoldEvent.HideBottomBar)

            ProductScreen(
                cartViewModel = cartViewModel,
                onNavigateToHome = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = DetailsDestinations.ExploreFilterScreen.route
        ) {
            scaffoldViewModel.onEvent(ScaffoldEvent.HideBottomBar)

            FilterScreen(
                onNavigateToExplore = {
                    navController.popBackStack()
                }
            )
        }
    }
}