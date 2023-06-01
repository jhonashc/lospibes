package com.example.lospibes.features.home.presentation.address_detail.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.lospibes.core.component.StandardTextField

@Composable
fun SideStreetTextField(
    value: String = "",
    isError: Boolean = false,
    onValueChange: (newValue: String) -> Unit
) {
    StandardTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        singleLine = true,
        shape = MaterialTheme.shapes.extraSmall,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        label = {
            Text(
                text = "Calle secundaria*",
                style = MaterialTheme.typography.titleMedium
            )
        },
        onValueChange = onValueChange
    )
}