package com.example.lospibes.core.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    isEnable: Boolean = true,
    singleLine: Boolean = false,
    readOnly: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(0.dp),
    label: @Composable() (() -> Unit)? = null,
    placeholder: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
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
        textStyle = textStyle,
        colors = colors,
        isError = isError,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        shape = shape,
        label = label,
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
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    shape: Shape = RoundedCornerShape(0.dp),
    label: @Composable() (() -> Unit)? = null,
    placeholder: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
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
        textStyle = textStyle,
        colors = colors,
        isError = isError,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        shape = shape,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onValueChange = onValueChange,
    )
}