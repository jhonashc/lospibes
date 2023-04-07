package com.example.lospibes.common.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.lospibes.common.domain.model.NavItem

@Composable
fun BottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navItems: List<NavItem>
) {
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        navItems.forEachIndexed() { index, screen ->
            NavigationBarItem(
                enabled = true,
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(screen.route)
                },
                label = {
                    Text(text = screen.name)
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.name
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}