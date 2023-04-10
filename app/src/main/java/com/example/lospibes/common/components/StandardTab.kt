package com.example.lospibes.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.domain.model.TabItem

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
            .clip(RoundedCornerShape(24.dp))
            .background(tabColor),
        selected = isSelected,
        onClick = onClick,
        selectedContentColor = MaterialTheme.colorScheme.background,
        unselectedContentColor = MaterialTheme.colorScheme.outline,
        text = {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                tabItem.icon?.let {
                    Text(
                        text = it,
                        fontWeight = tabFontWeight,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.padding(end = 8.dp))
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