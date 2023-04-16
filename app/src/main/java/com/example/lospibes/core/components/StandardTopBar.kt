package com.example.lospibes.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StandardTopBar(
    title: String? = null,
    onBackTo: () -> Unit,
    actions: @Composable () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onBackTo
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Back Icon"
            )
        }

        title?.let {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = it,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Row(
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            actions()
        }
    }
}