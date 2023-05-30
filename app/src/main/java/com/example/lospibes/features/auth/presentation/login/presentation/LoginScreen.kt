package com.example.lospibes.features.auth.presentation.login.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.domain.model.Auth
import com.example.lospibes.core.view_model.auth.AuthEvent
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.features.auth.component.EmailTextField
import com.example.lospibes.features.auth.component.PasswordTextField

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToOtp: (email: String) -> Unit
) {
    val loginState = loginViewModel.state.collectAsState()

    /* Temporal */
    val context = LocalContext.current

    LaunchedEffect(key1 = loginState.value.isActive) {
        if (!loginState.value.isActive) {
            onNavigateToOtp(loginState.value.email)
        }
    }

    /* Temporal */
    LaunchedEffect(key1 = loginState.value.isLoading) {
        if (loginState.value.message != null) {
            Toast.makeText(context, loginState.value.message, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = loginState.value.status) {
        if (loginState.value.status) {
            /* Success login */
            onNavigateToHome()

            authViewModel.onEvent(
                AuthEvent.SetAuthState(
                    Auth(
                        accessToken = loginState.value.accessToken ?: "",
                        refreshToken = loginState.value.refreshToken ?: "",
                        userId = loginState.value.userId ?: ""
                    )
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
        ) {
            IconButton(
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                onClick = onNavigateToHome
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "KeyboardArrowRight Icon"
                )
            }
        }

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
            text = "Los Pibes",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum egestas rhoncus feugiat. Ut eu viverra augue, non posuere erat. Aliquam at eleifend urna, quis mattis elit.",
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
            onValueChange = {
                loginViewModel.onEvent(LoginEvent.EnteredEmail(it))
            }
        )

        /* Password */
        PasswordTextField(
            value = loginState.value.password,
            onValueChange = {
                loginViewModel.onEvent(LoginEvent.EnteredPassword(it))
            }
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
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            enabled = !loginState.value.isLoading,
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = {
                loginViewModel.onEvent(LoginEvent.OnLoginClick)
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (loginState.value.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(25.dp),
                        color = MaterialTheme.colorScheme.background
                    )
                } else {
                    Text(
                        text = "Iniciar sesión",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
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
            text = "Registrate",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}