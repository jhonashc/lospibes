package com.example.lospibes.features.home.presentation.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.lospibes.features.home.viewmodel.scaffold.ScaffoldViewModel

@Composable
fun MainScreen(
    scaffoldViewModel: ScaffoldViewModel
) {
    val navController: NavHostController = rememberNavController()

    val scaffoldState = scaffoldViewModel.state.collectAsState()

    val homeNavItems: List<NavItem> = listOf(
        NavItem(
            name = stringResource(R.string.home_item),
            route = HomeDestinations.HomeScreen.route,
            icon = painterResource(id = R.drawable.baseline_home_24)
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
                id = R.drawable.baseline_shopping_bag_24
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
                id = R.drawable.baseline_account_circle_24
            )
        )
    )

    StandardScaffold(
        bottomAppBar = {
            if (scaffoldState.value.showBottomBar) {
                StandardBottomBar(
                    navController = navController,
                    navItems = homeNavItems
                )
            }
        }
    ) {
        HomeNavGraph(
            navController = navController,
            scaffoldViewModel = scaffoldViewModel
        )
    }
}