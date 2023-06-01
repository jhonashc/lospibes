package com.example.lospibes.features.home.presentation.profile_detail.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.lospibes.core.component.StandardCenterTopBar

@Composable
fun ProfileDetailTopBar(
    onNavigateToProfile: () -> Unit
) {
    StandardCenterTopBar(
        title = {
            Text(
                text = "Editar perfil",
                style = MaterialTheme.typography.titleMedium
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigateToProfile
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back Icon"
                )
            }
        }
    )
}