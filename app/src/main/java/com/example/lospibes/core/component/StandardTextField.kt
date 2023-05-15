package com.example.lospibes.core.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    isEnable: Boolean = true,
    singleLine: Boolean = false,
    readOnly: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(0.dp),
    placeholder: @Composable () -> Unit = {},
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    onValueChange: (newValue: String) -> Unit
) {
    val colors = TextFieldDefaults.colors(
        cursorColor = MaterialTheme.colorScheme.onBackground,
        focusedContainerColor = MaterialTheme.colorScheme.background,
        unfocusedContainerColor = MaterialTheme.colorScheme.background,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.outline,
        focusedPlaceholderColor = MaterialTheme.colorScheme.outline,
        focusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.outline,
        focusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.outline,
    )

    TextField(
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

@Composable
fun StandardOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    isEnable: Boolean = true,
    singleLine: Boolean = false,
    readOnly: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    shape: Shape = RoundedCornerShape(0.dp),
    placeholder: @Composable () -> Unit = {},
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    onValueChange: (newValue: String) -> Unit
) {
    val colors = TextFieldDefaults.colors(
        cursorColor = MaterialTheme.colorScheme.onBackground,
        focusedContainerColor = MaterialTheme.colorScheme.background,
        unfocusedContainerColor = MaterialTheme.colorScheme.background,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.outline,
        focusedPlaceholderColor = MaterialTheme.colorScheme.outline,
        focusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.outline,
        focusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.outline,

        )

    OutlinedTextField(
        modifier = modifier,
        value = value,
        enabled = isEnable,
        singleLine = singleLine,
        readOnly = readOnly,
        colors = colors,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        shape = shape,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onValueChange = onValueChange,
    )
}