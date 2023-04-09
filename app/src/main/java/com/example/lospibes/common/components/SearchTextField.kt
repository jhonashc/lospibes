package com.example.lospibes.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    value: String = "",
    isEnable: Boolean = true,
    showFilter: Boolean = false,
    onValueChange: (newValue: String) -> Unit,
    onClick: () -> Unit = {},
    onSubmit: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(24.dp),
        singleLine = true,
        enabled = isEnable,
        value = value,
        onValueChange = onValueChange,
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
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    IconButton(
                        onClick = onClick
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Filter",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (value.isNotEmpty()) {
                    onSubmit(value)
                }
            }
        )
    )
}