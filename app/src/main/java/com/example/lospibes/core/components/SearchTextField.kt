package com.example.lospibes.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.lospibes.R

@Composable
fun SearchTextField(
    value: String = "",
    showFilter: Boolean = false,
    onClick: () -> Unit,
    onValueChange: (newValue: String) -> Unit,
    onSubmit: (String) -> Unit
) {
    StandardTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                if (value.isNotEmpty()) {
                    onSubmit(value)
                }
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        shape = MaterialTheme.shapes.extraLarge,
        placeholder = {
            Text(
                text = "Buscar",
                color = MaterialTheme.colorScheme.outline
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.outline
            )
        },
        trailingIcon = {
            if (showFilter) {
                Box(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(40.dp)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    IconButton(
                        onClick = onClick
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.baseline_filter_alt_24
                            ),
                            contentDescription = "Filter",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }
        },
        onValueChange = onValueChange,
    )
}