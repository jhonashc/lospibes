package com.example.lospibes.features.auth.presentation.otp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.StandardOutlinedTextField
import com.example.lospibes.core.component.StandardTopBar

@Composable
fun OtpScreen(
    otpViewModel: OtpViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit
) {
    val otpState = otpViewModel.state.collectAsState()

    LaunchedEffect(key1 = otpState.value.message) {
        if (otpState.value.status) {
            onNavigateToLogin()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Header(
                onNavigateToLogin = onNavigateToLogin
            )
        }

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Body(
                otpViewModel = otpViewModel
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
private fun Body(
    otpViewModel: OtpViewModel
) {
    val otpState = otpViewModel.state.collectAsState()

    StandardOutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = otpState.value.otp,
        singleLine = true,
        shape = MaterialTheme.shapes.extraSmall,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        placeholder = {
            Text(
                text = "OTP here",
                style = MaterialTheme.typography.titleMedium
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Check Icon"
            )
        },
        onValueChange = {
            otpViewModel.onEvent(OtpEvent.EnteredOtp(it))
        }
    )

    Spacer(modifier = Modifier.height(26.dp))

    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.background
        ),
        onClick = {
            otpViewModel.onEvent(OtpEvent.OnSendOtp)
        }
    ) {
        if (otpState.value.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.background
            )
        } else {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Enviar",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}