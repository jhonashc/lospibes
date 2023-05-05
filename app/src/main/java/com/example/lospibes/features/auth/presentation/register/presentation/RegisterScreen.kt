package com.example.lospibes.features.auth.presentation.register.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.StandardTopBar
import com.example.lospibes.core.view_model.auth.AuthEvent
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.features.auth.presentation.register.component.EmailTextField
import com.example.lospibes.features.auth.presentation.register.component.PasswordTextField
import com.example.lospibes.features.auth.presentation.register.component.TelephoneTextField
import com.example.lospibes.features.auth.presentation.register.component.UsernameTextField

@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    registerViewModel: RegisterViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val registerState = registerViewModel.state.collectAsState()

    LaunchedEffect(key1 = registerState.value.token) {
        if (registerState.value.status) {
            onNavigateToHome()
            authViewModel.onEvent(AuthEvent.SetToken(registerState.value.token!!))
            authViewModel.onEvent(AuthEvent.SetUserId(registerState.value.userId!!))
        }
    }

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

            Body(
                registerViewModel = registerViewModel
            )

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
private fun Body(
    registerViewModel: RegisterViewModel
) {
    val registerState = registerViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        /* Username */
        UsernameTextField(
            value = registerState.value.username,
            onValueChange = {
                registerViewModel.onEvent(RegisterEvent.EnteredUsername(it))
            }
        )

        /* Email */
        EmailTextField(
            value = registerState.value.email,
            onValueChange = {
                registerViewModel.onEvent(RegisterEvent.EnteredEmail(it))
            }
        )

        /* Telephone */
        TelephoneTextField(
            value = registerState.value.telephone,
            onValueChange = {
                registerViewModel.onEvent(RegisterEvent.EnteredTelephone(it))
            }
        )

        /* Password */
        PasswordTextField(
            value = registerState.value.password,
            onValueChange = {
                registerViewModel.onEvent(RegisterEvent.EnteredPassword(it))
            }
        )

        /* Sign Up button */
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = {
                registerViewModel.onEvent(RegisterEvent.OnRegisterClick)
            }
        ) {
            if (registerState.value.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.background
                )
            } else {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Sign Up",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
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