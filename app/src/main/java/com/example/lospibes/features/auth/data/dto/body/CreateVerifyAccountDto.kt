package com.example.lospibes.features.auth.data.dto.body

data class CreateVerifyAccountDto(
    val email: String,
    val otp: String
)
