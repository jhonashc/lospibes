package com.example.lospibes.core.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lospibes.R
import com.example.lospibes.features.home.domain.model.NavItem
import com.example.lospibes.features.home.view_model.cart.CartViewModel

@Composable
fun StandardBottomBar(
    cartViewModel: CartViewModel,
    visible: Boolean = true,
    navController: NavHostController,
    navItems: List<NavItem>
) {
    val cartState = cartViewModel.state.collectAsState()

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(150),
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            animationSpec = tween(150),
            targetOffsetY = { it }
        ),
    ) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth()
        ) {
            navItems.forEach { navItem ->
                val selected = navItem.route == currentRoute

                NavigationBarItem(
                    enabled = true,
                    selected = selected,
                    onClick = {
                        navController.navigate(
                            route = navItem.route
                        ) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    label = {
                        Text(
                            text = navItem.name,
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    icon = {
                        if (navItem.name == stringResource(R.string.cart_item) &&
                            cartState.value.cartItemList.isNotEmpty()
                        ) {
                            StandardBadge(
                                badgeNumber = cartState.value.cartItemList.size,
                                icon = {
                                    Icon(
                                        painter = navItem.icon,
                                        contentDescription = navItem.name
                                    )
                                })
                        } else {
                            Icon(
                                painter = navItem.icon,
                                contentDescription = navItem.name
                            )
                        }
                    },
                    alwaysShowLabel = true
                )
            }
        }
    }
}