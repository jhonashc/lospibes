package com.example.lospibes.features.home.presentation.favorite.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.example.lospibes.core.component.StandardTopBar

@Composable
fun FavoriteTopBar(
    onNavigateToHome: () -> Unit
) {
    StandardTopBar(
        title = "Favoritos",
        navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Back Icon"
            )
        },
        onBackTo = onNavigateToHome
    )
}