package com.example.lospibes.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.features.auth.presentation.login.presentation.LoginScreen
import com.example.lospibes.features.auth.presentation.register.presentation.RegisterScreen
import com.example.lospibes.utils.Constants.AUTH_GRAPH_ROUTE
import com.example.lospibes.utils.Constants.HOME_GRAPH_ROUTE

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    navigation(
        route = AUTH_GRAPH_ROUTE,
        startDestination = AuthDestinations.LoginScreen.route,
    ) {
        composable(
            route = AuthDestinations.LoginScreen.route
        ) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToRegister = {
                    navController.navigate(AuthDestinations.RegisterScreen.route)
                },
                onNavigateToHome = {
                    navController.popBackStack()
                    navController.navigate(HOME_GRAPH_ROUTE)
                }
            )
        }

        composable(
            route = AuthDestinations.RegisterScreen.route
        ) {
            RegisterScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onNavigateToHome = {
                    navController.popBackStack()
                    navController.navigate(HOME_GRAPH_ROUTE)
                }
            )
        }
    }
}