package com.example.lospibes.core.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StandardTopBar(
    title: String? = null,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: @Composable () -> Unit = {},
    onBackTo: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        navigationIcon?.let { navIcon ->
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = onBackTo
            ) {
                navIcon()
            }
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
    filterIcon: (@Composable () -> Unit)? = null,
    onClose: () -> Unit,
    onSubmit: (value: String) -> Unit,
    onValueChange: (newValue: String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        StandardTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .align(Alignment.CenterStart)
                .padding(end = if (filterIcon != null) 40.dp else 0.dp)
                .focusRequester(focusRequester),
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
                        focusManager.clearFocus()
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
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.outline
                    ),
                    onClick = onClose
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Icon"
                    )
                }
            },
            onValueChange = onValueChange
        )

        filterIcon?.let { icon ->
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = { /*TODO*/ }
            ) {
                icon()
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
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