package com.example.lospibes.features.auth.presentation.login.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.lospibes.R
import com.example.lospibes.core.component.StandardOutlinedTextField

@Composable
fun PasswordTextField(
    value: String = "",
    onValueChange: (newValue: String) -> Unit
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    val passwordIcon = if (passwordVisibility)
        painterResource(id = R.drawable.baseline_remove_red_eye_24) else
        painterResource(id = R.drawable.outline_remove_red_eye_24)

    val visualTransformation = if (passwordVisibility)
        VisualTransformation.None else
        PasswordVisualTransformation()

    StandardOutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        singleLine = true,
        shape = MaterialTheme.shapes.extraSmall,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = visualTransformation,
        placeholder = {
            Text(
                text = "Contraseña",
                style = MaterialTheme.typography.titleMedium
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Lock Icon"
            )
        },
        trailingIcon = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.outline
                ),
                onClick = {
                    passwordVisibility = !passwordVisibility
                }
            ) {
                Icon(
                    painter = passwordIcon,
                    contentDescription = "Eye Icon"
                )
            }
        },
        onValueChange = onValueChange
    )
}