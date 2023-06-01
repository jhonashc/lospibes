package com.example.lospibes.features.home.presentation.address_detail.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.StandardScaffold
import com.example.lospibes.core.component.StandardScrollableColumnContainer
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.features.home.presentation.address_detail.component.AddressDetailTopBar
import com.example.lospibes.features.home.presentation.address_detail.component.AddressReferenceTextField
import com.example.lospibes.features.home.presentation.address_detail.component.NameTextField
import com.example.lospibes.features.home.presentation.address_detail.component.SideStreetTextField

@Composable
fun AddressDetailScreen(
    authViewModel: AuthViewModel,
    addressDetailViewModel: AddressDetailViewModel = hiltViewModel(),
    onNavigateToAddresses: () -> Unit
) {
    val authState = authViewModel.state.collectAsState()
    val addressDetailState = addressDetailViewModel.state.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        if (addressDetailState.value.id.isNotEmpty()) {
            addressDetailViewModel.getUserAddress(
                userId = authState.value.userId,
                addressId = addressDetailState.value.id
            )
        }
    }

    LaunchedEffect(key1 = addressDetailState.value.status) {
        if (addressDetailState.value.status &&
            addressDetailState.value.message != null
        ) {
            onNavigateToAddresses()
        }
    }

    LaunchedEffect(key1 = addressDetailState.value.message) {
        if (!addressDetailState.value.message.isNullOrEmpty()) {
            Toast.makeText(context, addressDetailState.value.message, Toast.LENGTH_SHORT).show()
        }
    }

    StandardScaffold(
        topAppBar = {
            Header(
                authViewModel = authViewModel,
                addressDetailViewModel = addressDetailViewModel,
                onNavigateToAddresses = onNavigateToAddresses
            )
        }
    ) {
        StandardScrollableColumnContainer(
            status = addressDetailState.value.status,
            isLoading = addressDetailState.value.isLoading,
            message = addressDetailState.value.message
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Body(
                    addressDetailViewModel = addressDetailViewModel
                )

                Spacer(modifier = Modifier.height(35.dp))

                Footer(
                    authViewModel = authViewModel,
                    addressDetailViewModel = addressDetailViewModel
                )
            }
        }
    }
}

@Composable
private fun Header(
    authViewModel: AuthViewModel,
    addressDetailViewModel: AddressDetailViewModel,
    onNavigateToAddresses: () -> Unit
) {
    val authState = authViewModel.state.collectAsState()
    val addressDetailState = addressDetailViewModel.state.collectAsState()

    val title = if (addressDetailState.value.id.isEmpty()) {
        "Agregar dirección"
    } else {
        "Editar dirección"
    }

    AddressDetailTopBar(
        title = title,
        isIconVisible = addressDetailState.value.id.isNotEmpty(),
        onDeleteClick = {
            if (addressDetailState.value.id.isNotEmpty()) {
                addressDetailViewModel.deleteUserAddress(
                    userId = authState.value.userId,
                    addressId = addressDetailState.value.id
                )
            }
        },
        onNavigateToAddresses = onNavigateToAddresses
    )
}

@Composable
private fun Body(
    addressDetailViewModel: AddressDetailViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        DetailSection(
            addressDetailViewModel = addressDetailViewModel
        )
    }
}

@Composable
fun DetailSection(
    addressDetailViewModel: AddressDetailViewModel
) {
    val addressDetailState = addressDetailViewModel.state.collectAsState()

    val isError = !addressDetailState.value.status &&
            addressDetailState.value.message != null

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        NameTextField(
            value = addressDetailState.value.name,
            isError = isError,
            onValueChange = {
                addressDetailViewModel.onEvent(AddressDetailEvent.EnteredName(it))
            }
        )

        SideStreetTextField(
            value = addressDetailState.value.sideStreet,
            isError = isError,
            onValueChange = {
                addressDetailViewModel.onEvent(AddressDetailEvent.EnteredSideStreet(it))
            }
        )

        AddressReferenceTextField(
            value = addressDetailState.value.deliveryInstruction,
            isError = isError,
            onValueChange = {
                addressDetailViewModel.onEvent(AddressDetailEvent.EnteredDeliveryInstruction(it))
            }
        )
    }
}

@Composable
private fun Footer(
    authViewModel: AuthViewModel,
    addressDetailViewModel: AddressDetailViewModel
) {
    val authState = authViewModel.state.collectAsState()
    val addressDetailState = addressDetailViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = {
                if (addressDetailState.value.id.isEmpty()) {
                    addressDetailViewModel.createUserAddress(
                        userId = authState.value.userId
                    )
                } else {
                    addressDetailViewModel.updateUserAddress(
                        userId = authState.value.userId,
                        addressId = addressDetailState.value.id
                    )
                }
            }
        ) {
            Text(
                text = "Guardar",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}