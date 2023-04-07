package com.example.lospibes.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.domain.model.Category

@Composable
fun CategoryTab(
    category: Category,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var fontWeight: FontWeight = FontWeight.Normal
    var tabColor: Color = MaterialTheme.colorScheme.background
    var tabContentColor: Color = MaterialTheme.colorScheme.secondary

    if (isSelected) {
        fontWeight = FontWeight.SemiBold
        tabColor = MaterialTheme.colorScheme.primary
        tabContentColor = MaterialTheme.colorScheme.background
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = tabColor,
        contentColor = tabContentColor,
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = category.name,
                fontWeight = fontWeight,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}