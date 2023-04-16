package com.example.lospibes.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StandardTopBar(
    title: String? = null,
    onBackTo: () -> Unit,
    actions: @Composable () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onBackTo
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Back Icon"
            )
        }

        title?.let {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = it,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Row(
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            actions()
        }
    }
}

@Composable
fun SearchTopBar(
    value: String = "",
    onClose: () -> Unit,
    onSubmit: (value: String) -> Unit,
    onValueChange: (newValue: String) -> Unit
) {

    StandardTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        value = value,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (value.isNotEmpty()) {
                    onSubmit(value)
                    onClose()
                }
            }
        ),
        placeholder = {
            Text(
                text = "Buscar",
                style = MaterialTheme.typography.titleMedium
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.outline
            )
        },
        trailingIcon = {
            IconButton(
                modifier = Modifier.padding(end = 5.dp),
                onClick = onClose
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Filter Icon",
                    tint = MaterialTheme.colorScheme.outline
                )
            }
        },
        onValueChange = onValueChange
    )
}

@Preview
@Composable
fun SearchTopBarPreview() {
    SearchTopBar(
        value = "",
        onSubmit = {},
        onClose = {},
        onValueChange = {}
    )
}