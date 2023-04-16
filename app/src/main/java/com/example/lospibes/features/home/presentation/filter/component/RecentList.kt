package com.example.lospibes.features.home.presentation.filter.component

import androidx.compose.runtime.Composable

@Composable
fun RecentList(
    itemList: List<String>,
    onItemSelected: (selectedItem: String) -> Unit
) {
    itemList.subList(0, 5).forEach { item ->
        RecentItem(
            item = item,
            onClick = { onItemSelected(item) }
        )
    }
}