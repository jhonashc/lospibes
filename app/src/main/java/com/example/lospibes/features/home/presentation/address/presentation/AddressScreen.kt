package com.example.lospibes.features.home.presentation.address.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
    onNavigateToNewAddress: () -> Unit,
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
                onNavigateToProfile = onNavigateToProfile,
                onNavigateToNewAddress = onNavigateToNewAddress
            )
        }
    ) {
        StandardColumnContainer(
            status = addressState.value.status,
            isLoading = addressState.value.isLoading,
            message = addressState.value.message
        ) {
            Body(
                addressViewModel = addressViewModel,
                onNavigateToDetails = onNavigateToDetails
            )
        }
    }
}

@Composable
private fun Header(
    onNavigateToProfile: () -> Unit,
    onNavigateToNewAddress: () -> Unit
) {
    AddressTopBar(
        onNavigateToProfile = onNavigateToProfile,
        onNavigateToNewAddress = onNavigateToNewAddress
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