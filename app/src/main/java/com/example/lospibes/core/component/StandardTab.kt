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
import androidx.compose.ui.unit.dp
import com.example.lospibes.features.home.domain.model.TabItem

@Composable
fun StandardTab(
    tabItem: TabItem,
    isSelected: Boolean = false,
    showIcon: Boolean = false,
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
                if (showIcon) {
                    tabItem.icon?.let {
                        Text(
                            modifier = Modifier.padding(end = 6.dp),
                            text = it,
                            fontWeight = tabFontWeight,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                Text(
                    text = tabItem.name,
                    fontWeight = tabFontWeight,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    )
}