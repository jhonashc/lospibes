package com.example.lospibes.features.home.presentation.address_detail.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.lospibes.core.component.StandardCenterTopBar

@Composable
fun AddressDetailTopBar(
    title: String,
    isIconVisible: Boolean = false,
    onDeleteClick: () -> Unit,
    onNavigateToAddresses: () -> Unit
) {
    StandardCenterTopBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigateToAddresses
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back Icon"
                )
            }
        },
        actions = {
            if (isIconVisible) {
                IconButton(
                    onClick = onDeleteClick
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete Icon"
                    )
                }
            }
        }
    )
}