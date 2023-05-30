package com.example.lospibes.features.home.presentation.explore.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lospibes.utils.formatNumber

@Composable
fun ExplorePriceSlider(
    value: ClosedFloatingPointRange<Float> = 1f..20f,
    valueRange: ClosedFloatingPointRange<Float> = 1f..20f,
    steps: Int = 4,
    onValueChange: (newValue: ClosedFloatingPointRange<Float>) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        RangeSlider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$ ${formatNumber(value.start)}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "$ ${formatNumber(value.endInclusive)}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}