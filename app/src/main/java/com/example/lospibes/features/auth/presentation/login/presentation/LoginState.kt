package com.example.lospibes.features.auth.presentation.login.presentation

data class LoginState(
    val status: Boolean = false,
    val message: String? = null,
    val isLoading: Boolean = false,
    val email: String = "admin@gmail.com",
    val password: String = "password",
    val userId: String? = null,
    val token: String? = null,
    val expiresIn: Int? = null
)
