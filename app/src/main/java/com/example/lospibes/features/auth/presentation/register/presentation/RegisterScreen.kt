package com.example.lospibes.features.auth.presentation.register.presentation

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.StandardScaffold
import com.example.lospibes.features.auth.component.EmailTextField
import com.example.lospibes.features.auth.component.PasswordTextField
import com.example.lospibes.features.auth.component.TelephoneTextField
import com.example.lospibes.features.auth.component.UsernameTextField
import com.example.lospibes.features.auth.presentation.register.component.RegisterTopBar

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
    onNavigateToOtp: (email: String) -> Unit
) {
    val registerState = registerViewModel.state.collectAsState()

    /* Temporal */
    val context = LocalContext.current

    /* Temporal */
    LaunchedEffect(key1 = registerState.value.isLoading) {
        if (registerState.value.message != null) {
            Toast.makeText(context, registerState.value.message, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = registerState.value.userId) {
        if (registerState.value.status) {
            /* Success register */
            onNavigateToOtp(registerState.value.email)
        }
    }

    StandardScaffold(
        topAppBar = {
            Header(
                onNavigateToLogin = onNavigateToLogin
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {
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
}

@Composable
private fun Header(
    onNavigateToLogin: () -> Unit
) {
    RegisterTopBar(
        onNavigateToLogin = onNavigateToLogin
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
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            enabled = !registerState.value.isLoading,
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = {
                registerViewModel.onEvent(RegisterEvent.OnRegisterClick)
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (registerState.value.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(25.dp),
                        color = MaterialTheme.colorScheme.background
                    )
                } else {
                    Text(
                        text = "Registrar",
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
            text = "Iniciar sesión",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}