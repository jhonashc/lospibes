package com.example.lospibes.common.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lospibes.common.domain.model.NavItem

@Composable
fun StandardBottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navItems: List<NavItem>
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        navItems.forEach { navItem ->
            val currentRoute = backStackEntry.value?.destination?.route
            val selected = navItem.route == currentRoute

            NavigationBarItem(
                enabled = true,
                selected = selected,
                onClick = {
                    if (navItem.route != currentRoute) {
                        navController.navigate(navItem.route)
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
                        imageVector = navItem.icon,
                        contentDescription = navItem.name
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}