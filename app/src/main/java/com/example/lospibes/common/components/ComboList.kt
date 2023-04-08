package com.example.lospibes.common.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.domain.model.Combo

@Composable
fun ComboList(
    combos: List<Combo>,
    onNavigateToComboDetails: (comboId: String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        itemsIndexed(combos) { index, combo ->
            Row(
                modifier = Modifier.padding(
                    start = if (index == 0) 20.dp else 0.dp,
                    end = if (combo.id == combos.last().id) 20.dp else 0.dp
                )
            )
            {
                ComboCard(
                    combo = combo,
                    onClick = {
                        onNavigateToComboDetails(combo.id)
                    }
                )
            }
        }
    }
}