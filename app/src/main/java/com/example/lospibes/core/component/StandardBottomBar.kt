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
    val currentRoute = backStackEntry.value?.destination?.route

    NavigationBar {
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