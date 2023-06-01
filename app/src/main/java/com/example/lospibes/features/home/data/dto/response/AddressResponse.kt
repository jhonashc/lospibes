package com.example.lospibes.features.home.data.dto.response

import com.example.lospibes.features.home.domain.model.Address

data class AddressesResponse(
    val data: List<Address>
)

data class AddressResponse(
    val data: Address,
    val message: String
)
