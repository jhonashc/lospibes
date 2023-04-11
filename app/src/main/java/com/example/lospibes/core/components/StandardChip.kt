package com.example.lospibes.core.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lospibes.features.home.domain.model.ChipItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardChip(
    chipItem: ChipItem,
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
        label = {
            Text(
                text = chipItem.name,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        trailingIcon = {
            chipItem.icon?.let {
                Icon(
                    modifier = Modifier.size(AssistChipDefaults.IconSize),
                    imageVector = it,
                    contentDescription = "Remove",
                    tint = iconTint
                )
            }
        },
        onClick = onClick
    )
}