package com.example.lospibes.modules.home.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lospibes.navigation.home.HomeDestinations
import com.example.lospibes.navigation.home.HomeNavGraph
import com.example.lospibes.common.components.BottomAppBar
import com.example.lospibes.common.components.StandardScaffold
import com.example.lospibes.common.domain.model.NavItem
import com.example.lospibes.R

@Composable
fun HomeRootScreen() {
    val navController: NavHostController = rememberNavController()

    StandardScaffold(
        bottomAppBar = {
            BottomAppBar(
                navController = navController,
                navItems = listOf(
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
                        name = stringResource(R.string.cart_item),
                        route = HomeDestinations.AccountScreen.route,
                        icon = Icons.Rounded.Person,
                    ),
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        HomeNavGraph(navController = navController)
    }
}