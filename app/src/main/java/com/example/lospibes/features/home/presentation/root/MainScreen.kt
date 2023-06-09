package com.example.lospibes.features.home.presentation.root

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lospibes.navigation.home.HomeDestinations
import com.example.lospibes.navigation.home.HomeNavGraph
import com.example.lospibes.core.component.StandardScaffold
import com.example.lospibes.core.component.StandardBottomBar
import com.example.lospibes.features.home.domain.model.NavItem
import com.example.lospibes.R
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.core.view_model.snackbar.SnackBarViewModel
import com.example.lospibes.features.home.view_model.cart.CartViewModel
import com.example.lospibes.features.home.view_model.scaffold.ScaffoldViewModel

@Composable
fun MainScreen(
    authViewModel: AuthViewModel,
    cartViewModel: CartViewModel,
    scaffoldViewModel: ScaffoldViewModel,
    snackBarViewModel: SnackBarViewModel
) {
    val navController: NavHostController = rememberNavController()

    val scaffoldState = scaffoldViewModel.state.collectAsState()
    val snackBarState = snackBarViewModel.state.collectAsState()

    val homeNavItems: List<NavItem> = listOf(
        NavItem(
            name = stringResource(R.string.home_item),
            route = HomeDestinations.HomeScreen.route,
            icon = painterResource(
                id = R.drawable.baseline_home_24
            )
        ),
        NavItem(
            name = stringResource(R.string.explore_item),
            route = HomeDestinations.ExploreScreen.route,
            icon = painterResource(
                id = R.drawable.baseline_explore_24
            )
        ),
        NavItem(
            name = stringResource(R.string.cart_item),
            route = HomeDestinations.CartScreen.route,
            icon = painterResource(
                id = R.drawable.baseline_shopping_basket_24
            )
        ),
        NavItem(
            name = stringResource(R.string.favorite_item),
            route = HomeDestinations.FavoriteScreen.route,
            icon = painterResource(
                id = R.drawable.baseline_favorite_24
            )
        ),
        NavItem(
            name = stringResource(R.string.account_item),
            route = HomeDestinations.ProfileScreen.route,
            icon = painterResource(
                id = R.drawable.baseline_person_24
            )
        )
    )

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = snackBarState.value) {
        if (snackBarState.value.message.isNotEmpty()) {
            snackBarHostState.showSnackbar(
                message = snackBarState.value.message
            )
        }
    }

    StandardScaffold(
        snackBarHost = { SnackbarHost(snackBarHostState) },
        bottomAppBar = {
            StandardBottomBar(
                cartViewModel = cartViewModel,
                visible = scaffoldState.value.showBottomBar,
                navController = navController,
                navItems = homeNavItems
            )
        }
    ) {
        HomeNavGraph(
            navController = navController,
            authViewModel = authViewModel,
            cartViewModel = cartViewModel,
            scaffoldViewModel = scaffoldViewModel
        )
    }
}