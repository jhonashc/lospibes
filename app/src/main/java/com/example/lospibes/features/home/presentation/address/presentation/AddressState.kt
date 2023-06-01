package com.example.lospibes.features.home.presentation.address.presentation

import com.example.lospibes.features.home.domain.model.Address

data class AddressState(
    val status: Boolean = false,
    val message: String? = null,
    val isLoading: Boolean = true,
    val addressList: List<Address> = emptyList(),
)
