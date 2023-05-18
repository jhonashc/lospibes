package com.example.lospibes.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.features.auth.presentation.login.presentation.LoginScreen
import com.example.lospibes.features.auth.presentation.otp.presentation.OtpScreen
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
                },
                onNavigateToOtp = { email ->
                    navController.navigate("${AuthDestinations.OtpScreen.route}/${email}")
                }
            )
        }

        composable(
            route = AuthDestinations.RegisterScreen.route
        ) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onNavigateToOtp = { email ->
                    navController.navigate("${AuthDestinations.OtpScreen.route}/${email}")
                }
            )
        }

        composable(
            route = "${AuthDestinations.OtpScreen.route}/{email}",
            arguments = listOf(
                navArgument("email") {
                    defaultValue = ""
                    type = NavType.StringType
                }
            )
        ) {
            OtpScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                    navController.navigate(AUTH_GRAPH_ROUTE)
                }
            )
        }
    }
}