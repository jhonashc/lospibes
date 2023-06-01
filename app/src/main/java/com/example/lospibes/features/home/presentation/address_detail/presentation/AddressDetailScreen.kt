package com.example.lospibes.features.home.presentation.address_detail.presentation

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.StandardScaffold
import com.example.lospibes.core.component.StandardScrollableColumnContainer
import com.example.lospibes.features.home.presentation.address_detail.component.AddressDetailTopBar
import com.example.lospibes.features.home.presentation.address_detail.component.AddressReferenceTextField
import com.example.lospibes.features.home.presentation.address_detail.component.NameTextField
import com.example.lospibes.features.home.presentation.address_detail.component.SideStreetTextField

@Composable
fun AddressDetailScreen(
    addressDetailViewModel: AddressDetailViewModel = hiltViewModel(),
    onNavigateToAddresses: () -> Unit
) {
    val addressDetailState = addressDetailViewModel.state.collectAsState()

    StandardScaffold(
        topAppBar = {
            Header(
                addressDetailViewModel = addressDetailViewModel,
                onNavigateToAddresses = onNavigateToAddresses
            )
        }
    ) {
        StandardScrollableColumnContainer(
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

                Footer(
                    addressDetailViewModel = addressDetailViewModel,
                    onNavigateToAddresses = onNavigateToAddresses
                )
            }
        }
    }
}

@Composable
private fun Header(
    addressDetailViewModel: AddressDetailViewModel,
    onNavigateToAddresses: () -> Unit
) {
    val addressDetailState = addressDetailViewModel.state.collectAsState()

    val title = if (addressDetailState.value.id.isEmpty()) {
        "Agregar dirección"
    } else {
        "Editar dirección"
    }

    AddressDetailTopBar(
        title = title,
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

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        NameTextField(
            value = addressDetailState.value.name,
            onValueChange = {
                addressDetailViewModel.onEvent(AddressDetailEvent.EnteredName(it))
            }
        )

        SideStreetTextField(
            value = addressDetailState.value.sideStreet,
            onValueChange = {
                addressDetailViewModel.onEvent(AddressDetailEvent.EnteredSideStreet(it))
            }
        )

        AddressReferenceTextField(
            value = addressDetailState.value.addressReference ?: "",
            onValueChange = {
                addressDetailViewModel.onEvent(AddressDetailEvent.EnteredAddressReference(it))
            }
        )
    }
}

@Composable
private fun Footer(
    addressDetailViewModel: AddressDetailViewModel,
    onNavigateToAddresses: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
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
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Guardar",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}