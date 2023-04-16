package com.example.lospibes.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    val fontWeight = if (isSelected)
        FontWeight.Bold else
        FontWeight.Normal

    AssistChip(
        colors = AssistChipDefaults.assistChipColors(
            containerColor = containerColor,
            labelColor = labelColor
        ),
        label = {
            Text(
                modifier = Modifier.padding(vertical = 7.dp),
                text = chipItem.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = fontWeight
            )
        },
        trailingIcon = {
            chipItem.icon?.let {
                Icon(
                    modifier = Modifier.size(AssistChipDefaults.IconSize),
                    imageVector = it,
                    contentDescription = "Remove Icon",
                    tint = iconTint
                )
            }
        },
        onClick = onClick
    )
}

@Preview
@Composable
fun StandardChipPreview() {
    StandardChip(
        chipItem = ChipItem(
            name = "Chip 1",
            icon = Icons.Filled.Favorite
        ),
        onClick = {}
    )
}