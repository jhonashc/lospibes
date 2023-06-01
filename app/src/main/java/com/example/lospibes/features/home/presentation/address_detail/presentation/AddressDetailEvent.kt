package com.example.lospibes.features.home.presentation.address_detail.presentation

sealed class AddressDetailEvent {
    data class EnteredName(val value: String) : AddressDetailEvent()
    data class EnteredSideStreet(val value: String) : AddressDetailEvent()
    data class EnteredDeliveryInstruction(val value: String) : AddressDetailEvent()
}
