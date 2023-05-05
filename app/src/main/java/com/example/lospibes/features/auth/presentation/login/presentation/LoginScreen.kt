package com.example.lospibes.features.auth.presentation.login.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.lospibes.core.view_model.auth.AuthEvent
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.features.auth.presentation.login.component.EmailTextField
import com.example.lospibes.features.auth.presentation.login.component.PasswordTextField

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val loginState = loginViewModel.state.collectAsState()

    LaunchedEffect(key1 = loginState.value.token) {
        loginState.value.token?.let { validToken ->
            onNavigateToHome()

            authViewModel.onEvent(AuthEvent.SetToken(validToken))
            authViewModel.onEvent(AuthEvent.SetUserId(loginState.value.user?.id.orEmpty()))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Header()

            Spacer(modifier = Modifier.height(35.dp))

            Body(
                loginViewModel = loginViewModel
            )

            Spacer(modifier = Modifier.height(30.dp))

            Footer(
                onNavigateToRegister = onNavigateToRegister
            )
        }
    }
}

@Composable
private fun Header() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Sign In",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Se necesitan unos pocos toques para comenzar a ordenar.",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
private fun Body(
    loginViewModel: LoginViewModel
) {
    val loginState = loginViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        /* Email */
        EmailTextField(
            value = loginState.value.email,
            onValueChange = { loginViewModel.onEvent(LoginEvent.EnteredEmail(it)) }
        )

        /* Password */
        PasswordTextField(
            value = loginState.value.password,
            onValueChange = { loginViewModel.onEvent(LoginEvent.EnteredPassword(it)) }
        )

        /* Forgot password */
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "¿Te has olvidado la contraseña?",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.primary
        )

        /* Sign In button */
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = {
                loginViewModel.login()
            }
        ) {
            if (loginState.value.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.background
                )
            } else {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Sign In",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun Footer(
    onNavigateToRegister: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(end = 6.dp),
            text = "¿No tienes una cuenta?",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            modifier = Modifier.clickable(onClick = onNavigateToRegister),
            text = "Sign Up",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}