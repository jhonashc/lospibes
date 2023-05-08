package com.example.lospibes.core.view_model.auth

data class AuthState(
    val accessToken: String = "",
    val refreshToken: String = "",
    val userId: String = ""
)
