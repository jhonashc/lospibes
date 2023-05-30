package com.example.lospibes.features.auth.presentation.register.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.lospibes.core.component.StandardCenterTopBar

@Composable
fun RegisterTopBar(
    onNavigateToLogin: () -> Unit
) {
    StandardCenterTopBar(
        navigationIcon = {
            IconButton(
                onClick = onNavigateToLogin
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back Icon"
                )
            }
        }
    )
}