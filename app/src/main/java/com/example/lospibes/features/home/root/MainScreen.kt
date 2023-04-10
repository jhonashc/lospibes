package com.example.lospibes.features.home.root

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lospibes.navigation.home.HomeDestinations
import com.example.lospibes.navigation.home.HomeNavGraph
import com.example.lospibes.common.components.StandardScaffold
import com.example.lospibes.common.components.StandardBottomAppBar
import com.example.lospibes.common.domain.model.NavItem
import com.example.lospibes.R

@Composable
fun MainScreen() {
    val navController: NavHostController = rememberNavController()

    val homeNavItems: List<NavItem> = listOf(
        NavItem(
            name = stringResource(R.string.home_item),
            route = HomeDestinations.HomeScreen.route,
            icon = Icons.Rounded.Home,
        ),
        NavItem(
            name = stringResource(R.string.explore_item),
            route = HomeDestinations.ExploreScreen.route,
            icon = Icons.Rounded.Search,
        ),
        NavItem(
            name = stringResource(R.string.cart_item),
            route = HomeDestinations.CartScreen.route,
            icon = Icons.Rounded.ShoppingCart,
        ),
        NavItem(
            name = stringResource(R.string.favorite_item),
            route = HomeDestinations.FavoriteScreen.route,
            icon = Icons.Rounded.Favorite,
        ),
        NavItem(
            name = stringResource(R.string.account_item),
            route = HomeDestinations.ProfileScreen.route,
            icon = Icons.Rounded.AccountCircle,
        ),
    )

    StandardScaffold(
        bottomAppBar = {
            StandardBottomAppBar(
                navController = navController,
                navItems = homeNavItems
            )
        }
    ) {
        HomeNavGraph(navController = navController)
    }
}