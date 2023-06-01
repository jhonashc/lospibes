package com.example.lospibes.features.home.presentation.address_detail.presentation

data class AddressDetailState(
    val status: Boolean = true,
    val message: String? = null,
    val isLoading: Boolean = true,
    val id: String = "",
    val name: String = "",
    val sideStreet: String = "",
    val deliveryInstruction: String = ""
)