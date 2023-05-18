package com.example.lospibes.features.auth.presentation.otp.presentation

data class OtpState(
    val status: Boolean = false,
    val message: String? = null,
    val isLoading: Boolean = false,
    val otp: String = ""
)
