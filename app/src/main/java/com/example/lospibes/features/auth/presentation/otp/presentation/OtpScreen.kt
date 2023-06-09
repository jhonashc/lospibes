package com.example.lospibes.features.auth.presentation.otp.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.lospibes.features.auth.presentation.otp.component.OtpTextField
import com.example.lospibes.features.auth.presentation.otp.component.OtpTopBar

@Composable
fun OtpScreen(
    otpViewModel: OtpViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit
) {
    val otpState = otpViewModel.state.collectAsState()

    /* Temporal */
    val context = LocalContext.current

    LaunchedEffect(key1 = otpState.value.message) {
        if (otpState.value.message != null) {
            Toast.makeText(context, otpState.value.message, Toast.LENGTH_SHORT).show()
        }

        if (otpState.value.status) {
            onNavigateToLogin()
        }
    }

    StandardScaffold(
        topAppBar = {
            Header(
                onNavigateToLogin = onNavigateToLogin
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Body(
                    otpViewModel = otpViewModel
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Footer(
                    otpViewModel = otpViewModel
                )
            }
        }
    }
}

@Composable
private fun Header(
    onNavigateToLogin: () -> Unit
) {
    OtpTopBar(
        onNavigateToLogin = onNavigateToLogin
    )
}

@Composable
private fun Body(
    otpViewModel: OtpViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        OtpView(
            otpViewModel = otpViewModel
        )
    }
}

@Composable
private fun OtpView(
    otpViewModel: OtpViewModel
) {
    val otpState = otpViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Verificación de cuenta",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Ingrese el código OTP enviado al correo ${otpViewModel.email}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.outline,
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(24.dp))

        OtpTextField(
            value = otpState.value.otp,
            onValueChange = {
                if (it.length <= 5) {
                    otpViewModel.onEvent(OtpEvent.EnteredOtp(it.uppercase()))
                }
            }
        )
    }
}

@Composable
private fun Footer(
    otpViewModel: OtpViewModel
) {
    val otpState = otpViewModel.state.collectAsState()

    val textColor = if (otpState.value.isLoading) {
        MaterialTheme.colorScheme.outline
    } else {
        MaterialTheme.colorScheme.primary
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(26.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            enabled = !otpState.value.isLoading,
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = {
                otpViewModel.onEvent(OtpEvent.OnSubmit)
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (otpState.value.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(25.dp),
                        color = MaterialTheme.colorScheme.background
                    )
                } else {
                    Text(
                        text = "Enviar",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(end = 6.dp),
                text = "¿No recibiste el código OTP?",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.outline
            )

            Text(
                modifier = Modifier.clickable(
                    onClick = {
                        if (!otpState.value.isLoading) {
                            otpViewModel.onEvent(OtpEvent.OnResend)
                        }
                    }
                ),
                text = "Reenviar",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}