package com.example.lospibes.navigation.root

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lospibes.features.home.presentation.root.MainScreen
import com.example.lospibes.features.home.viewmodel.scaffold.ScaffoldViewModel
import com.example.lospibes.navigation.auth.authNavGraph
import com.example.lospibes.utils.Constants.HOME_GRAPH_ROUTE
import com.example.lospibes.utils.Constants.ROOT_GRAPH_ROUTE

@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    val scaffoldViewModel: ScaffoldViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        route = ROOT_GRAPH_ROUTE,
        startDestination = HOME_GRAPH_ROUTE,
    ) {
        authNavGraph(navController = navController)

        composable(route = HOME_GRAPH_ROUTE) {
            MainScreen(
                scaffoldViewModel = scaffoldViewModel
            )
        }
    }
}