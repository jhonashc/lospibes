package com.example.lospibes.features.home.presentation.address.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.StandardColumnContainer
import com.example.lospibes.core.component.StandardScaffold
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.features.home.presentation.address.component.AddressList
import com.example.lospibes.features.home.presentation.address.component.AddressTopBar

@Composable
fun AddressScreen(
    authViewModel: AuthViewModel,
    addressViewModel: AddressViewModel = hiltViewModel(),
    onNavigateToProfile: () -> Unit,
    onNavigateToNew: () -> Unit,
    onNavigateToDetails: (addressId: String) -> Unit
) {
    val authState = authViewModel.state.collectAsState()
    val addressState = addressViewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        addressViewModel.getAddresses(
            userId = authState.value.userId
        )
    }

    StandardScaffold(
        topAppBar = {
            Header(
                onNavigateToProfile = onNavigateToProfile
            )
        }
    ) {
        StandardColumnContainer(
            isLoading = addressState.value.isLoading,
            message = addressState.value.message
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Body(
                        addressViewModel = addressViewModel,
                        onNavigateToDetails = onNavigateToDetails
                    )
                }

                Column(
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    Footer(
                        onNavigateToNew = onNavigateToNew
                    )
                }
            }
        }
    }
}

@Composable
private fun Header(
    onNavigateToProfile: () -> Unit
) {
    AddressTopBar(
        onNavigateToProfile = onNavigateToProfile
    )
}

@Composable
private fun Body(
    addressViewModel: AddressViewModel,
    onNavigateToDetails: (addressId: String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AddressSection(
            addressViewModel = addressViewModel,
            onNavigateToDetails = onNavigateToDetails
        )
    }
}

@Composable
private fun AddressSection(
    addressViewModel: AddressViewModel,
    onNavigateToDetails: (addressId: String) -> Unit
) {
    val addressState = addressViewModel.state.collectAsState()

    AddressList(
        addressList = addressState.value.addressList,
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
private fun Footer(
    onNavigateToNew: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = onNavigateToNew
        ) {
            Text(
                text = "Agregar dirección",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}