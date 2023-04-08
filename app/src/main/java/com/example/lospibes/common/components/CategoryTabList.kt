package com.example.lospibes.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.domain.model.Category

@Composable
fun CategoryTabList(
    categories: List<Category>,
    selectedCategory: Category,
    onCategorySelected: (Category) -> Unit,
    onNavigateToSearch: (category: String) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = categories.indexOf(selectedCategory),
        contentColor = MaterialTheme.colorScheme.onSurface,
        edgePadding = 8.dp,
        indicator = {},
        divider = {}
    ) {
        categories.forEach { category ->
            CategoryTab(
                category = category,
                isSelected = category.id == selectedCategory.id,
                onClick = {
                    onCategorySelected(category)
                    onNavigateToSearch(category.name)
                },
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
            )
        }
    }
}