package com.example.lospibes.features.home.presentation.address.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lospibes.features.home.domain.model.Address

@Composable
fun AddressList(
    addressList: List<Address>,
    onNavigateToDetails: (addressId: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(addressList) { address ->
            AddressItem(
                address = address,
                onClick = {
                    onNavigateToDetails(address.id)
                }
            )
        }
    }
}