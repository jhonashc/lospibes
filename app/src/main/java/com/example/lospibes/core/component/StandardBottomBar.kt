package com.example.lospibes.core.component

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lospibes.features.home.domain.model.NavItem

@Composable
fun StandardBottomBar(
    navController: NavHostController,
    navItems: List<NavItem>
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar {
        navItems.forEach { navItem ->
            val currentRoute = backStackEntry.value?.destination?.route
            val selected = navItem.route == currentRoute

            NavigationBarItem(
                enabled = true,
                selected = selected,
                onClick = {
                    if (navItem.route != currentRoute) {
                        navController.navigate(navItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                label = {
                    Text(
                        text = navItem.name,
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                icon = {
                    Icon(
                        painter = navItem.icon,
                        contentDescription = navItem.name
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}