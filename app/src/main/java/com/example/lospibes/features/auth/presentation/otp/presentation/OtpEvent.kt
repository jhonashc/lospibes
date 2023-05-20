package com.example.lospibes.features.auth.presentation.otp.presentation

sealed class OtpEvent {
    data class EnteredOtp(val value: String): OtpEvent()
    object OnSubmit : OtpEvent()
}
