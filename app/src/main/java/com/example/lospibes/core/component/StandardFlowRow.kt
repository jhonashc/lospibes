package com.example.lospibes.core.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.features.home.domain.model.ChipItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StandardFlowRow(
    modifier: Modifier = Modifier,
    itemList: List<String>,
    selectedItem: String,
    maxItemsInEachRow: Int = 3,
    onItemSelected: (item: String) -> Unit
) {
    FlowRow(
        modifier = modifier,
        maxItemsInEachRow = maxItemsInEachRow,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        itemList.forEach { item ->
            StandardChip(
                chipItem = ChipItem(name = item),
                isSelected = item == selectedItem,
                onClick = { onItemSelected(item) }
            )
        }
    }
}