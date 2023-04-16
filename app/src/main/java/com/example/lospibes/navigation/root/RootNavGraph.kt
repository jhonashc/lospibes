package com.example.lospibes.navigation.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lospibes.features.home.presentation.root.MainScreen
import com.example.lospibes.navigation.auth.authNavGraph
import com.example.lospibes.utils.Constants.AUTH_GRAPH_ROUTE
import com.example.lospibes.utils.Constants.HOME_GRAPH_ROUTE
import com.example.lospibes.utils.Constants.ROOT_GRAPH_ROUTE

@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = ROOT_GRAPH_ROUTE,
        startDestination = AUTH_GRAPH_ROUTE,
    ) {
        authNavGraph(navController = navController)

        composable(route = HOME_GRAPH_ROUTE) {
            MainScreen()
        }
    }
}