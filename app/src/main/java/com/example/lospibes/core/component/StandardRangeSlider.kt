package com.example.lospibes.core.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardRangeSlider(
    modifier: Modifier = Modifier,
    value: ClosedFloatingPointRange<Float>,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (newValue: ClosedFloatingPointRange<Float>) -> Unit,
    steps: Int
) {
    RangeSlider(
        modifier = modifier,
        value = value,
        valueRange = valueRange,
        onValueChange = onValueChange,
        steps = steps
    )
}