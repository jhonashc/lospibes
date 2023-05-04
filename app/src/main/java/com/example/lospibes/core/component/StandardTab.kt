package com.example.lospibes.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.example.lospibes.features.home.domain.model.TabItem
import com.example.lospibes.utils.capitalizeText

@Composable
fun StandardTab(
    tabItem: TabItem,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val tabColor = if (isSelected)
        MaterialTheme.colorScheme.primary else
        MaterialTheme.colorScheme.background

    val tabFontWeight = if (isSelected)
        FontWeight.SemiBold else
        FontWeight.Normal

    Tab(
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(tabColor),
        selected = isSelected,
        onClick = onClick,
        selectedContentColor = MaterialTheme.colorScheme.background,
        unselectedContentColor = MaterialTheme.colorScheme.outline,
        text = {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = capitalizeText(tabItem.name),
                    fontWeight = tabFontWeight,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    )
}