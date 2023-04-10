package com.example.lospibes.common.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardChip(
    text: String = "",
    showTrailingIcon: Boolean = false,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val containerColor = if (isSelected)
        MaterialTheme.colorScheme.primary else
        MaterialTheme.colorScheme.background

    val labelColor = if (isSelected)
        MaterialTheme.colorScheme.background else
        MaterialTheme.colorScheme.outline

    val iconTint = if (isSelected)
        MaterialTheme.colorScheme.background else
        MaterialTheme.colorScheme.outline

    AssistChip(
        colors = AssistChipDefaults.assistChipColors(
            containerColor = containerColor,
            labelColor = labelColor
        ),
        onClick = onClick,
        label = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        trailingIcon = {
            if (showTrailingIcon) {
                Icon(
                    modifier = Modifier.size(AssistChipDefaults.IconSize),
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Remove",
                    tint = iconTint
                )
            }
        }
    )
}