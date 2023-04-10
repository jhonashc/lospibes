package com.example.lospibes.common.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StandardFlowRow(
    itemList: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.padding(horizontal = 20.dp),
        maxItemsInEachRow = 5,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        itemList.forEach { item ->
            StandardChip(
                text = item,
                isSelected = item == selectedItem,
                onClick = {
                    onItemSelected(item)
                }
            )
        }
    }
}