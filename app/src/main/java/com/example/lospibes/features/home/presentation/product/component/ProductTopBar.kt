package com.example.lospibes.features.home.presentation.product.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.lospibes.core.component.StandardCenterTopBar

@Composable
fun ProductTopBar(
    isFavorite: Boolean,
    onNavigateToHome: () -> Unit
) {
    val favoriteIcon = if (isFavorite)
        Icons.Filled.Favorite else
        Icons.Filled.FavoriteBorder

    val favoriteContentButtonColor = if (isFavorite)
        MaterialTheme.colorScheme.error else
        MaterialTheme.colorScheme.onBackground

    StandardCenterTopBar(
        navigationIcon = {
            IconButton(
                onClick = onNavigateToHome
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back Icon"
                )
            }
        },
        actions = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = favoriteContentButtonColor
                ),
                onClick = { /* TODO */ }
            ) {
                Icon(
                    imageVector = favoriteIcon,
                    contentDescription = "Favorite Icon"
                )
            }
        }
    )
}