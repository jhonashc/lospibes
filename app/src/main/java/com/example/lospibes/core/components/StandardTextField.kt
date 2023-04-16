package com.example.lospibes.core.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    isEnable: Boolean = true,
    singleLine: Boolean = false,
    readOnly: Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholder: @Composable () -> Unit = {},
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    onValueChange: (newValue: String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        enabled = isEnable,
        singleLine = singleLine,
        readOnly = readOnly,
        colors = colors,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onValueChange = onValueChange,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    isEnable: Boolean = true,
    singleLine: Boolean = false,
    readOnly: Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(0.dp),
    placeholder: @Composable () -> Unit = {},
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    onValueChange: (newValue: String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        enabled = isEnable,
        singleLine = singleLine,
        readOnly = readOnly,
        colors = colors,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        shape = shape,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onValueChange = onValueChange,
    )
}