package com.example.lospibes.features.home.presentation.filter.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.lospibes.R
import com.example.lospibes.core.component.StandardOutlinedTextField

@Composable
fun MaxPriceTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (newValue: String) -> Unit
) {
    StandardOutlinedTextField(
        modifier = modifier,
        value = value,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onNext = {}
        ),
        shape = MaterialTheme.shapes.extraSmall,
        placeholder = {
            Text(
                text = "Máximo",
                style = MaterialTheme.typography.titleMedium
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(
                    id = R.drawable.baseline_attach_money_24
                ),
                contentDescription = "Money Icon"
            )
        },
        onValueChange = onValueChange
    )
}