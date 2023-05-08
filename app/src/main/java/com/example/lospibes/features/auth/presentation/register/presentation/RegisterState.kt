package com.example.lospibes.features.auth.presentation.register.presentation

data class RegisterState(
    val status: Boolean = false,
    val message: String? = null,
    val isLoading: Boolean = false,
    val username: String = "",
    val email: String = "",
    val telephone: String = "",
    val password: String = "",
    val userId: String? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null
)
