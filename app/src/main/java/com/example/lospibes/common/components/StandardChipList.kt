package com.example.lospibes.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StandardChipList(
    shipList: List<String>,
    showTrailingIcon: Boolean = false,
    onClick: () -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(shipList) { index, shipText ->
            Row(
                modifier = Modifier.padding(
                    start = if (index == 0) 20.dp else 0.dp,
                    end = if (shipText == shipList.last()) 20.dp else 0.dp
                )
            ) {
                StandardChip(
                    text = shipText,
                    showTrailingIcon = showTrailingIcon,
                    onClick = onClick
                )
            }
        }
    }
}