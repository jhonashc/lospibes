package com.example.lospibes.features.auth.presentation.login.presentation

sealed class LoginEvent {
    data class EnteredEmail(val value: String) : LoginEvent()
    data class EnteredPassword(val value: String) : LoginEvent()
}
