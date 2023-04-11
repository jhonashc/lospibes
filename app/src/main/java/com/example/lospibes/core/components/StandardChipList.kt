package com.example.lospibes.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.features.home.domain.model.ChipItem

@Composable
fun StandardChipList(
    chipList: List<ChipItem>,
    onClick: () -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(chipList) { index, chip ->
            Row(
                modifier = Modifier.padding(
                    start = if (index == 0) 20.dp else 0.dp,
                    end = if (chip == chipList.last()) 20.dp else 0.dp
                )
            ) {
                StandardChip(
                    chipItem = chip,
                    onClick = onClick
                )
            }
        }
    }
}