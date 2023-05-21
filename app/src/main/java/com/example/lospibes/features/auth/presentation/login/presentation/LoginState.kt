package com.example.lospibes.features.auth.presentation.login.presentation

data class LoginState(
    val status: Boolean = false,
    val message: String? = null,
    val isLoading: Boolean = false,
    val email: String = "jhonhuiracocha123456789@gmail.com",
    val password: String = "password",
    val userId: String? = null,
    val isActive: Boolean = true,
    val accessToken: String? = null,
    val refreshToken: String? = null
)
