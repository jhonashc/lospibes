package com.example.lospibes.features.auth.presentation.register.presentation

sealed class RegisterEvent {
    data class EnteredUsername(val value: String) : RegisterEvent()
    data class EnteredEmail(val value: String) : RegisterEvent()
    data class EnteredTelephone(val value: String) : RegisterEvent()
    data class EnteredPassword(val value: String) : RegisterEvent()
    object OnRegisterClick: RegisterEvent()
}
