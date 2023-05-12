package com.example.lospibes.navigation.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.features.home.presentation.root.MainScreen
import com.example.lospibes.features.home.view_model.cart.CartViewModel
import com.example.lospibes.features.home.view_model.scaffold.ScaffoldViewModel
import com.example.lospibes.navigation.auth.authNavGraph
import com.example.lospibes.utils.Constants.AUTH_GRAPH_ROUTE
import com.example.lospibes.utils.Constants.HOME_GRAPH_ROUTE
import com.example.lospibes.utils.Constants.ROOT_GRAPH_ROUTE

@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val cartViewModel: CartViewModel = hiltViewModel()
    val scaffoldViewModel: ScaffoldViewModel = hiltViewModel()

    val authState = authViewModel.state.collectAsState()

    if (!authState.value.isLoading) {
        val startDestination = if (!authState.value.isAuthenticated)
            AUTH_GRAPH_ROUTE else
            HOME_GRAPH_ROUTE

        NavHost(
            navController = navController,
            route = ROOT_GRAPH_ROUTE,
            startDestination = startDestination,
        ) {
            authNavGraph(
                navController = navController,
                authViewModel = authViewModel
            )

            composable(route = HOME_GRAPH_ROUTE) {
                MainScreen(
                    authViewModel = authViewModel,
                    cartViewModel = cartViewModel,
                    scaffoldViewModel = scaffoldViewModel
                )
            }
        }
    }
}