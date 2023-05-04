package com.example.lospibes.features.auth.presentation.register.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lospibes.R
import com.example.lospibes.core.component.StandardOutlinedTextField
import com.example.lospibes.core.component.StandardTopBar

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
        ) {
            Header(
                onNavigateToLogin = onNavigateToLogin
            )
        }

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Banner()

            Spacer(modifier = Modifier.height(35.dp))

            Body()

            Spacer(modifier = Modifier.height(30.dp))

            Footer(
                onNavigateToLogin = onNavigateToLogin
            )
        }
    }
}

@Composable
private fun Header(
    onNavigateToLogin: () -> Unit
) {
    StandardTopBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Back Icon"
            )
        },
        onBackTo = onNavigateToLogin
    )
}

@Composable
fun Banner() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Se necesitan unos pocos toques para registrarte y comenzar a ordenar.",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
private fun Body() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        /* Username */
        StandardOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            singleLine = true,
            shape = MaterialTheme.shapes.extraLarge,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(
                    text = "Nombre de usuario",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Person Icon"
                )
            },
            onValueChange = {}
        )

        /* Email */
        StandardOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            singleLine = true,
            shape = MaterialTheme.shapes.extraLarge,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Email Icon"
                )
            },
            onValueChange = {}
        )

        /* Telephone */
        StandardOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            singleLine = true,
            shape = MaterialTheme.shapes.extraLarge,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(
                    text = "Teléfono",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "Phone Icon"
                )
            },
            onValueChange = {}
        )

        /* Password */
        StandardOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            singleLine = true,
            shape = MaterialTheme.shapes.extraLarge,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
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
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
                        contentDescription = "Eye Icon"
                    )
                }
            },
            onValueChange = {}
        )

        /* Sign Up button */
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = { /*TODO*/ }
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Sign Up",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun Footer(
    onNavigateToLogin: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(end = 6.dp),
            text = "¿Ya tienes una cuenta?",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            modifier = Modifier.clickable(onClick = onNavigateToLogin),
            text = "Sign In",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}