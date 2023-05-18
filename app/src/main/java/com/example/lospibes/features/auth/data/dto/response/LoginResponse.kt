package com.example.lospibes.features.auth.data.dto.response

data class LoginResponse(
    val userId: String,
    val accessToken: String,
    val refreshToken: String
)
